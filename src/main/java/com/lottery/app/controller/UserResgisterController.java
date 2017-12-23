package com.lottery.app.controller;

import com.lottery.app.services.interfaces.IUserRegister;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason: 用户注册控制器
 */
@Controller
public class UserResgisterController {
    private static Logger log = Logger.getLogger(UserResgisterController.class);

    @Resource
    IUserRegister registerService;

    @RequestMapping({"/register"})
    public String register() {

        return "register";
    }

}
