package com.lottery.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.app.domain.User;
import com.lottery.app.service.interfaces.IUser.IUserRegister;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason: 用户注册控制器
 */
@RestController
@RequestMapping(value = "/register")
public class UserResgisterController {
    private static Logger log = Logger.getLogger(UserResgisterController.class);
    private IUserRegister userService;

    @RequestMapping(value = "/action", method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpServletResponse response) {
        int rst = 0;
        try {
            String body = _getRequestBody(request);
            JSONObject jsonObject = JSON.parseObject(body);
            String telephoneNum = jsonObject.getString("telephoneNum");
            User user = _getUserDbObject(telephoneNum);
            rst = userService.insertNewUser(user);
        } catch (IOException e) {
            if (log.isEnabledFor(Priority.ERROR))
                log.error("注册用户出错。", e);
            return null;
        }
        if (rst == 1)
            return "{\n" +
                    "    \"success\": \"注册成功\"\n" +
                    "}";
        else
            return "{\n" +
                    "\"error\"" + " : \"xxxx\",\n" +
                    "\"error_code\"" + " : " + rst;
    }

    private User _getUserDbObject(String telephoneNum) {
        User user = new User();
        Date date = new Date(System.currentTimeMillis());
        user.setPhoneNum(telephoneNum);
        user.setRegistDate(date);
        user.setModifyDate(date);
        return user;
    }

    private String _getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream stream = request.getInputStream();
        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        stream.read(buffer, 0, len);
        return new String(buffer, "utf-8");
    }

    @Autowired
    @Qualifier("userService")
    public void setUserService(IUserRegister userService) {
        this.userService = userService;
    }

}
