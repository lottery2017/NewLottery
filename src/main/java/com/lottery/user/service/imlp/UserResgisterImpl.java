package com.lottery.user.service.imlp;

import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.ValidSMSResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.user.dao.UserMapper;
import com.lottery.user.dao.UserVerifycodeMapper;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.user.domain.User;
import com.lottery.user.domain.UserVerifycode;
import com.lottery.user.service.interfaces.IUser.IUserRegister;
import com.lottery.util.ShareCodeUtil;
import com.lottery.util.exception.ResponeseCodes;
import com.lottery.util.exception.UserRuntimeException;
import com.lottery.util.verifycode.SendMS;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason:
 */
@Service("userRegister")
public class UserResgisterImpl implements IUserRegister {
    private static Logger logger = Logger.getLogger(UserResgisterImpl.class);
    private UserMapper userService;
    private UserVerifycodeMapper userVerifycodeMapper;

    public String registerWithVerifyCode(String body) {
        ResultStatus resultStatus = new ResultStatus();
        List<UserVerifycode> userVerifycode = null;
        try {
            //获取请求报文
            JSONObject jsonObject = JSON.parseObject(body);
            String telephoneNum = jsonObject.getString("phone_num");
            String verifyCode = jsonObject.getString("verify_code");
            //推荐码
            String recommonderCode = jsonObject.getString("invite_code");

            //验证验证码
            userVerifycode = userVerifycodeMapper.selectByPhone(telephoneNum);
            if (userVerifycode == null || userVerifycode.size() ==0)
                throw new UserRuntimeException();
            String messageid = userVerifycode.get(userVerifycode.size() == 1 ? 0 : userVerifycode.size() - 1).getMessageid();
            ValidSMSResult smsCodeValid = SendMS.isSMSCodeValid(messageid, verifyCode);
            if (smsCodeValid == null || !smsCodeValid.getIsValid()) {
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000006);
                resultStatus.setStatusInfo("验证码非法");
                return JSON.toJSONString(resultStatus);
            }

            //新注册用户存库
            //根据手机号生成邀请码
            String invite_code = ShareCodeUtil.idToCode(telephoneNum);
            User user = _getUserDbObject(telephoneNum, invite_code, recommonderCode);
            if (userService.insert(user) == 1) {
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000001);
                resultStatus.setStatusInfo("注册成功");
                resultStatus.setInvite_code(invite_code);
            } else {
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000003);
                resultStatus.setStatusInfo("注册失败!数据库异常。");
            }
        } catch (APIRequestException e) {
            //验证码非法
            _setRstMessge(resultStatus, e);
        } catch (UserRuntimeException e) {
            if (logger.isEnabledFor(Priority.ERROR))
                logger.error("注册用户出错。", e);
            resultStatus.setStatusInfo("验证码已经过期或者输入错误,请重试");
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000006);
        } finally {
            //删除该用户最新的message信息
            if (userVerifycode != null) {
                for (UserVerifycode verifycode : userVerifycode
                        ) {
                    userVerifycodeMapper.deleteByPrimaryKey(verifycode.getId());
                }
            }
        }
        return JSON.toJSONString(resultStatus);
    }

    private void _setRstMessge(ResultStatus resultStatus, APIRequestException e) {
        if (e.getErrorCode() == 50010) {
            resultStatus.setStatusInfo("验证码已经过期或者输入错误,请重试");
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000006);
        } else {
            resultStatus.setStatusInfo("短信模块出现未知异常:" + e.getErrorMessage());
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
        }
    }

    public User findUser(User user) {
        return userService.selectByPhone(user.getPhoneNum());
    }

    private User _getUserDbObject(String telephoneNum, String invite_code, String recommenderCode) {
        User user = new User();
        Date date = new Date(System.currentTimeMillis());

        if (recommenderCode != null && recommenderCode.length() > 0)
            user.setRecommender(recommenderCode);
        user.setInviteCode(invite_code);
        user.setPhoneNum(telephoneNum);
        user.setRegistDate(date);
        user.setModifyDate(date);
        user.setValidTag("Y");
        return user;
    }

    @Autowired
    public void setUserVerifycodeMapper(UserVerifycodeMapper userVerifycodeMapper) {
        this.userVerifycodeMapper = userVerifycodeMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userService = userMapper;
    }


}
