package com.lottery.user.controller;

import com.alibaba.fastjson.JSON;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.user.service.interfaces.IUser.IUserLogin;
import com.lottery.util.RequestUtil;
import com.lottery.util.exception.ResponeseCodes;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IUserLogin userLogin;

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String rsp = null;
        ResultStatus resultStatus = new ResultStatus();
        try {
            String body = RequestUtil._getRequestBody(request);
            rsp = userLogin.login(body);
        } catch (IOException e) {
            if (log.isEnabledFor(Priority.ERROR))
                log.error("获取请求报文失败。", e);
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
            resultStatus.setStatusInfo("获取请求报文失败。");
            return JSON.toJSONString(resultStatus);
        }
        return rsp;
    }
}