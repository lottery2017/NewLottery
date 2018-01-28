package com.lottery.account.controller;

import com.lottery.account.service.impl.AccountAuthService;
import com.lottery.account.service.impl.BindingCardService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gaojunc on 2018/1/16 16:06.
 * Created Reason: 用户账户controller
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {
    private static Logger log = Logger.getLogger(AccountController.class);
    private AccountAuthService accountAuthService;
    private BindingCardService bindingCardService;

    /**
     *用户实名认证
     */
    @RequestMapping(value = "/realNameAuth", method = RequestMethod.POST)
    public String realNameAuthentication(HttpServletRequest request){
        return accountAuthService.doAuth(request);
    }

    /**
     * 用户绑定银行卡
     */
    @RequestMapping(value = "/bindcard", method = RequestMethod.POST)
    public String bindingBankCard(HttpServletRequest request){
        return bindingCardService.doBinding(request);
    }

    @Autowired
    @Qualifier("realNameAuth")
    public void setAccountAuthService(AccountAuthService accountAuthService) {
        this.accountAuthService = accountAuthService;
    }

    @Autowired
    public void setBindingCardService(BindingCardService bindingCardService) {
        this.bindingCardService = bindingCardService;
    }
}
