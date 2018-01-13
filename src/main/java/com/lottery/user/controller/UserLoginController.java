package com.lottery.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.user.domain.User;
import com.lottery.user.service.interfaces.IUser.IUserLogin;
import com.lottery.user.service.interfaces.IUser.IUserRegister;
import com.lottery.util.RequestUtil;
import com.lottery.util.exception.ResponeseCodes;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by gaojunc on 2017/12/28 19:35.
 * Created Reason:
 */
@RestController
@RequestMapping(value = "/login")
public class UserLoginController {
    private static Logger log = Logger.getLogger(UserLoginController.class);
    private IUserLogin userLogin;
    private IUserRegister userRegister;

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String rsp = null;
        ResultStatus resultStatus = new ResultStatus();
        try {
            String body = RequestUtil._getRequestBody(request);
            JSONObject jsonObject = JSON.parseObject(body);
            String phoneNum = jsonObject.getString("phone_num");
            if (_checkIfExists(phoneNum))
                rsp = userLogin.loginWithVerifyCode(body);
            else {
                //注册新用户
                rsp = userRegister.registerWithVerifyCode(body);
            }
        } catch (IOException e) {
            if (log.isEnabledFor(Priority.ERROR))
                log.error("获取请求报文失败。", e);
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
            resultStatus.setStatusInfo("获取请求报文失败。");
            return JSON.toJSONString(resultStatus);
        }
        return rsp;
    }

    /*
     *  判断用户是否注册
     */
    private boolean _checkIfExists(String phoneNum) {
        User user = new User();
        user.setPhoneNum(phoneNum);
        User rst = userRegister.findUser(user);
        if (rst != null) {
            return true;
        }
        return false;
    }

    @Autowired
    @Qualifier("userRegister")
    public void setUserService(IUserRegister userRegister) {
        this.userRegister = userRegister;
    }

    @Autowired
    @Qualifier("userLogin")
    public void setUserLogin(IUserLogin userLogin) {
        this.userLogin = userLogin;
    }
}