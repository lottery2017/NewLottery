package com.lottery.account.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.account.dao.AccountMapper;
import com.lottery.account.dao.AccountRecordMapper;
import com.lottery.account.domain.Account;
import com.lottery.account.domain.AccountRecord;
import com.lottery.account.domain.BatchPayWS.BatchPayServiceLocator;
import com.lottery.account.domain.complatible.BankRequestBean;
import com.lottery.account.domain.complatible.BankResponseBean;
import com.lottery.user.dao.UserMapper;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.user.domain.User;
import com.lottery.util.RequestUtil;
import com.lottery.util.exception.ResponeseCodes;
import com.lottery.util.md5.MD5Util;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gaojunc on 2018/1/31 10:45.
 * Created Reason: 用户充值/提现服务
 */
@Service
public class CashService {
    private static Logger log = Logger.getLogger(CashService.class);
    private AccountMapper accountMapper;
    private UserMapper userMapper;
    private AccountRecordMapper accountRecordMapper;

    public String withdraw(HttpServletRequest request) {
        ResultStatus resultStatus = new ResultStatus();
        try {
            String body = RequestUtil._getRequestBody(request);
            JSONObject jsonObject = JSON.parseObject(body);
            String nick_name = jsonObject.getString("nick_name");
            double withdraw_cash = jsonObject.getDouble("withdraw_cash");
            User user = userMapper.selectByNickName(nick_name);
            Account account = accountMapper.selectByPrimaryKey(user.getUserid());
            BigDecimal balance = account.getBalance();
            if (balance.doubleValue() < withdraw_cash) {
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
                resultStatus.setStatusInfo("账户余额不足。");
                return JSON.toJSONString(resultStatus);
            }
            BankResponseBean bankResponseBean = doWithdraw(user, account, balance.doubleValue(), "");
            if (!bankResponseBean.isResultFlag()){
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
                resultStatus.setStatusInfo(bankResponseBean.getFailureCause());
                return JSON.toJSONString(resultStatus);
            }else {//提现成功
                //保存订单记录
                _saveOrder(withdraw_cash, user, bankResponseBean);
                //修改账户余额
                account.setBalance(account.getBalance().subtract(new BigDecimal(withdraw_cash)));
                account.setModifyTime(new Date(System.currentTimeMillis()));
                accountMapper.updateByPrimaryKeySelective(account);
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000001);
                resultStatus.setStatusInfo("提现成功!");
                return JSON.toJSONString(resultStatus);
            }
        } catch (IOException e) {
            if (log.isEnabledFor(Priority.ERROR))
                log.error("获取请求报文失败。", e);
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
            resultStatus.setStatusInfo("获取请求报文失败。");
            return JSON.toJSONString(resultStatus);
        } catch (ServiceException e) {
            if (log.isEnabledFor(Priority.ERROR))
                log.error(e);
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
            resultStatus.setStatusInfo(e.getMessage());
            return JSON.toJSONString(resultStatus);
        }
//        return JSON.toJSONString(resultStatus);
    }

    private void _saveOrder(double withdraw_cash, User user, BankResponseBean bankResponseBean){
        AccountRecord accountRecord = new AccountRecord();
        accountRecord.setUserid(user.getUserid());
        accountRecord.setMoney(new BigDecimal(withdraw_cash));
        accountRecord.setOrderContent("");
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = null;
        try {
            parse = sd.parse(bankResponseBean.getOrderId());
        } catch (ParseException e) {
            log.error("解析订单日期出错，使用默认时间", e);
            accountRecord.setOrderTime(new Date(System.currentTimeMillis()));
        }
        accountRecord.setOrderTime(parse);
        accountRecord.setOrderType(1);
        accountRecord.setWithdrawType("bank");
        accountRecordMapper.insert(accountRecord);
    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setAccountRecordMapper(AccountRecordMapper accountRecordMapper) {
        this.accountRecordMapper = accountRecordMapper;
    }

    public BankResponseBean doWithdraw(User user, Account account, double cashAmount, String remark) throws UnsupportedEncodingException, ServiceException, RemoteException {
        //客户编号所对应的密钥。。在账户邮箱中获取
        String key = "34JRNBT28EUZZGTQ";
        //城市,中文字符 主要只需要城市名，不需要省份名。也不要带"市""自治区（县）"等
        String[] location = account.getBankLocation().split("_");
        if (location.length != 2)
            throw new IllegalArgumentException("银行卡信息不完整，请输入银行所在城市。");
        String province_city = location[1];
        //银行名称 请填写银行的标准名,详见接口文档
        String bankName = account.getBankNum();
        //银行卡开户行的名称
        String kaihuhang = account.getBankDeposit();
        //收款人姓名,收款人的姓名，必须与银行卡账户名相同
        String creditName = user.getUsername();
        //银行卡号
        String bankCardNumber = account.getBankNum();
        //交易金额  整数或小数  小数为两位以内  但小数点的最后一位不能为0 如：0.10不行。单位为人民币元
        String amount = String.valueOf(cashAmount);
        //订单号
        String orderId = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        //组合字符串。。必须按照此顺序组串
        String macVal = bankCardNumber + amount + orderId + key;
        String mac = MD5Util.md5Hex(macVal.getBytes("gb2312")).toUpperCase();

        BankRequestBean requestBean = new BankRequestBean();
        requestBean.setProvince_city(province_city);
        requestBean.setBankName(bankName);
        requestBean.setKaihuhang(kaihuhang);
        requestBean.setCreditName(creditName);
        requestBean.setBankCardNumber(bankCardNumber);
        requestBean.setAmount(Double.parseDouble(amount));
        //交易备注
        requestBean.setDescription(remark);
        requestBean.setOrderId(orderId);
        requestBean.setMac(mac);
        BankRequestBean[] queryArray = new BankRequestBean[1];
        queryArray[0] = requestBean;

        String merchant_id = "10209994030";
        String merchant_ip = "127.0.0.1";
        BatchPayServiceLocator locator = new BatchPayServiceLocator();
        BankResponseBean[] responseBean = locator.getBatchPayWS().bankPay(queryArray, merchant_id, merchant_ip);
        return responseBean[0];
    }
}
