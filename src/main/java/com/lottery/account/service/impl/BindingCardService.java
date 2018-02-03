package com.lottery.account.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.account.dao.AccountMapper;
import com.lottery.account.domain.Account;
import com.lottery.user.dao.UserMapper;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.util.RequestUtil;
import com.lottery.util.exception.ResponeseCodes;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gaojunc on 2018/1/19 16:27.
 * Created Reason: 绑定银行卡服务
 */
@Service
public class BindingCardService {
    private static Logger log = Logger.getLogger(BindingCardService.class);
    private AccountMapper accountMapper;
    private UserMapper userMapper;
    public String doBinding(HttpServletRequest request){
        String body = null;
        ResultStatus resultStatus = new ResultStatus();
        try {
            body = RequestUtil._getRequestBody(request);
            JSONObject jsonObject = JSON.parseObject(body);
            String binding_phone = jsonObject.getString("binding_phone");
            if (userMapper.selectByPhone(binding_phone) == null){
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000008);
                resultStatus.setStatusInfo("当前手机号码没有注册。");
                return JSON.toJSONString(resultStatus);
            }
            String bank_name = jsonObject.getString("bank_name");
            String bank_province = jsonObject.getString("bank_province");
            String bank_city = jsonObject.getString("bank_city");
            String deposit_bank = jsonObject.getString("deposit_bank");
            String card_num = jsonObject.getString("card_num");
            Account account = new Account();
            account.setBankNum(card_num);
            account.setBindingPhone(binding_phone);
            account.setBankLocation(bank_province+ "_" + bank_city);
            account.setBankName(bank_name);
            account.setBankDeposit(deposit_bank);
            account.setValidTag("Y");
            account.setUserid("key_" + binding_phone);
            account.setBalance(new BigDecimal(0));
            Date time = new Date(System.currentTimeMillis());
            account.setCreateTime(time);
            account.setModifyTime(time);
            if (accountMapper.insert(account) == 1){
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000001);
                resultStatus.setStatusInfo("银行卡绑定成功");
            }
            else {
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
                resultStatus.setStatusInfo("银行卡绑定失败");
            }
        } catch (IOException e) {
            if (log.isEnabledFor(Priority.ERROR))
                log.error("获取请求报文失败。", e);
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
            resultStatus.setStatusInfo("获取请求报文失败。");
            return JSON.toJSONString(resultStatus);
        }
        return JSON.toJSONString(resultStatus);
    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
