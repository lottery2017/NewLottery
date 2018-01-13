package com.lottery.user.controller;

import cn.jsms.api.SendSMSResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.user.dao.UserVerifycodeMapper;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.user.domain.UserVerifycode;
import com.lottery.util.RequestUtil;
import com.lottery.util.exception.ResponeseCodes;
import com.lottery.util.verifycode.SendMS;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

        SendSMSResult sendSMSResult = SendMS.toSendSMSCode(phoneNum);
        //判断是否发送成功
        if (sendSMSResult.getResponseCode() == 200) {
            //如果发送成功将该条信息存库
            String messageId = sendSMSResult.getMessageId();
            resultStatus.setStatusInfo("验证码已发送");
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000001);
            _saveMessageId(phoneNum, messageId);
            return JSON.toJSONString(resultStatus);
        } else {
            //失败直接返回报文
            resultStatus.setStatusInfo(sendSMSResult.toString());
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
            return JSON.toJSONString(resultStatus);
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



    @Autowired
    public void setUserVerifycodeMapper(UserVerifycodeMapper userVerifycodeMapper) {
        this.userVerifycodeMapper = userVerifycodeMapper;
    }
}
