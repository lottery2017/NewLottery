package com.lottery.user.controller;

import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.ValidSMSResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.user.dao.UserVerifycodeMapper;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.user.domain.User;
import com.lottery.user.domain.UserVerifycode;
import com.lottery.user.service.interfaces.IUser.IUserRegister;
import com.lottery.util.RequestUtil;
import com.lottery.util.exception.ResponeseCodes;
import com.lottery.util.exception.UserRuntimeException;
import com.lottery.util.verifycode.SendMS;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason: 用户注册控制器
 */
@RestController
@RequestMapping(value = "/users/register")
public class UserResgisterController {
    private static Logger log = Logger.getLogger(UserResgisterController.class);
    private IUserRegister userService;
    private UserVerifycodeMapper userVerifycodeMapper;

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String register(HttpServletRequest request) {
        int rst = 0;
        ResultStatus resultStatus = new ResultStatus();
        UserVerifycode userVerifycode = null;
        try {
            //获取请求报文
            String body = RequestUtil._getRequestBody(request);
            JSONObject jsonObject = JSON.parseObject(body);
            String telephoneNum = jsonObject.getString("phone_num");
            String verifyCode = jsonObject.getString("verify_code");

            //验证验证码
             userVerifycode = userVerifycodeMapper.selectByPhone(telephoneNum);
             if (userVerifycode == null)
                 throw new UserRuntimeException();
            String messageid = userVerifycode.getMessageid();
            ValidSMSResult smsCodeValid = SendMS.isSMSCodeValid(messageid, verifyCode);
            if (smsCodeValid == null || !smsCodeValid.getIsValid()){
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000006);
                resultStatus.setStatusInfo("验证码非法");
                return JSON.toJSONString(resultStatus);
            }

            //新注册用户存库
            User user = _getUserDbObject(telephoneNum);
            rst = userService.insertNewUser(user);

            if (rst == 1) {
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000001);
                resultStatus.setStatusInfo("注册成功");
            }else {
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000003);
                resultStatus.setStatusInfo("注册失败!数据库异常。");
            }
        } catch (IOException e) {
           log.error(e);
        } catch (APIRequestException e) {
            //验证码非法
            if (e.getErrorCode() == 50010) {
                resultStatus.setStatusInfo("验证码已经过期或者输入错误,请重试");
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000006);
            }
        } catch (UserRuntimeException e) {
            if (log.isEnabledFor(Priority.ERROR))
                log.error("注册用户出错。", e);
            resultStatus.setStatusInfo("验证码已经过期或者输入错误,请重试");
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000006);
        } finally {
            //删除该用户最新的message信息
            if (userVerifycode != null)
            userVerifycodeMapper.deleteByPrimaryKey(userVerifycode.getId());
        }
        return JSON.toJSONString(resultStatus);
    }

    private User _getUserDbObject(String telephoneNum) {
        User user = new User();
        Date date = new Date(System.currentTimeMillis());
        user.setPhoneNum(telephoneNum);
        user.setRegistDate(date);
        user.setModifyDate(date);
        return user;
    }

    @Autowired
    @Qualifier("userRegister")
    public void setUserService(IUserRegister userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserVerifycodeMapper(UserVerifycodeMapper userVerifycodeMapper) {
        this.userVerifycodeMapper = userVerifycodeMapper;
    }
}
