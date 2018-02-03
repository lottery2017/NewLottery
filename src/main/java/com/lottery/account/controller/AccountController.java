package com.lottery.account.controller;

import com.lottery.account.service.impl.AccountAuthService;
import com.lottery.account.service.impl.BindingCardService;
import com.lottery.account.service.impl.CashService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gaojunc on 2018/1/16 16:06.
 * Created Reason: 用户账户controller
 */
@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private static Logger log = Logger.getLogger(AccountController.class);
    private AccountAuthService accountAuthService;
    private BindingCardService bindingCardService;
    private CashService cashService;

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

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdrawCash(HttpServletRequest request){
        return  cashService.withdraw(request);
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

    @Autowired
    public void setCashService(CashService cashService) {
        this.cashService = cashService;
    }
}
