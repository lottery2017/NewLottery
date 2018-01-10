package com.lottery.user.controller;

import cn.jsms.api.SendSMSResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.user.dao.UserVerifycodeMapper;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.user.domain.User;
import com.lottery.user.domain.UserVerifycode;
import com.lottery.user.service.interfaces.IUser.IUser;
import com.lottery.util.RequestUtil;
import com.lottery.util.exception.ResponeseCodes;
import com.lottery.util.verifycode.SendMS;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Created by gaojunc on 2018/1/4 16:44.
 * Created Reason:
 */
@RestController
@RequestMapping(value = "/verifycode")
public class VerifyCodeController {
    private static Logger log = Logger.getLogger(VerifyCodeController.class);
    private UserVerifycodeMapper userVerifycodeMapper;
    private IUser userService;

    @RequestMapping("/register/getverifycode")
    @ResponseBody
    public String sendSMSCode(HttpServletRequest request) {
        String body = null;
        ResultStatus resultStatus = new ResultStatus();
        try {
            body = RequestUtil._getRequestBody(request);
        } catch (IOException e) {
            log.error("获取请求报文异常，", e);
        }
        JSONObject jsonObject = JSON.parseObject(body);
        String phoneNum = jsonObject.getString("phone_num");

        if (_checkIfExists(resultStatus, phoneNum)) return JSON.toJSONString(resultStatus);

        SendSMSResult sendSMSResult = SendMS.toSendSMSCode(phoneNum);
        //判断是否发送成功
        if (sendSMSResult.getResponseCode() == 200) {
            //如果发送成功将该条信息存库
            String messageId = sendSMSResult.getMessageId();
            _saveMessageId(phoneNum, messageId);
            return "{'success':'验证码已发送'}";
        } else {
            //失败直接返回报文
            return sendSMSResult.toString();
        }
    }

    private void _saveMessageId(String phoneNum, String messageId) {
        UserVerifycode userVerifycode = new UserVerifycode();
        userVerifycode.setId(messageId + "_" + String.valueOf(System.currentTimeMillis()));
        userVerifycode.setPhonenum(phoneNum);
        userVerifycode.setMessageid(messageId);
        userVerifycode.setOptTime(new Date(System.currentTimeMillis()));
        userVerifycodeMapper.insert(userVerifycode);
    }

    private boolean _checkIfExists(ResultStatus resultStatus, String phoneNum) {
        User user = new User();
        user.setPhoneNum(phoneNum);
        User rst = userService.findUser(user);
        if (rst != null) {
            resultStatus.setStatusInfo("用户已经存在！");
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000002);
            return true;
        }
        return false;
    }

    @Autowired
    @Qualifier("userRegister")
    public void setUserService(IUser userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserVerifycodeMapper(UserVerifycodeMapper userVerifycodeMapper) {
        this.userVerifycodeMapper = userVerifycodeMapper;
    }
}
