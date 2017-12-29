package com.lottery.app.controller;

import com.alibaba.fastjson.JSON;
import com.lottery.app.domain.Model.ResultStatus;
import com.lottery.app.service.interfaces.IUser.IUserLogin;
import com.lottery.app.util.ErrorCodes;
import com.lottery.app.util.exception.ResponeseCodes;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaojunc on 2017/12/28 19:35.
 * Created Reason:
 */
@RestController
@RequestMapping(value = "login")
public class UserLoginController {
    private static Logger log = Logger.getLogger(UserResgisterController.class);
    private IUserLogin userLogin;

    @RequestMapping(value = "/action", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        String rsp = null;
        try {
            String body = _getRequestBody(request);
            rsp = userLogin.login(body);
        } catch (IOException e) {
            if (log.isEnabledFor(Priority.ERROR))
                log.error("用户用户出错。", e);
            ResultStatus resultStatus = new ResultStatus();
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
            resultStatus.setStatusInfo("注册用户出错。");
            return JSON.toJSONString(resultStatus);
        }
        return rsp;
    }

    private String _getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream stream = request.getInputStream();
        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        stream.read(buffer, 0, len);
        return new String(buffer, "utf-8");
    }

}
