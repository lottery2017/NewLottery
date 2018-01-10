package com.lottery.util.verifycode;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SendMS {

    protected static final Logger LOG = LoggerFactory.getLogger(SendMS.class);

    private static String appkey = "";
    private static String masterSecret = "";
    public static final String SOURCE_PATH = "/commonConfigs.properties";

    static {
        _initSMSConfig();
    }

    private static void _initSMSConfig() {
        InputStream fis = null;
        try {
            fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(SOURCE_PATH);
            if (fis == null)
                return;
            Properties prps = new Properties();
            prps.load(fis);
            appkey = prps.getProperty("appkey");
            masterSecret = prps.getProperty("masterSecret");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static SendSMSResult toSendSMSCode(String phoneNum) {
        SMSClient client = new SMSClient(masterSecret, appkey);
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(phoneNum)
                .setTempId(1)
                .build();
        SendSMSResult res = null;
        try {
            res = client.sendSMSCode(payload);
            System.out.println(res.toString());
            LOG.info(res.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
        return res;
    }


    public static ValidSMSResult isSMSCodeValid(String msgId, String code) throws APIRequestException {
        SMSClient client = new SMSClient(masterSecret, appkey);
        ValidSMSResult res = null;
        try {
            res = client.sendValidSMSCode(msgId, code);
            LOG.info(res.toString());
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            e.printStackTrace();
            System.out.println(e.getStatus() + " errorCode: " + e.getErrorCode() + " " + e.getErrorMessage());
            throw e;
            //LOG.error("Error response from JPush server. Should review and fix it. ", e);
            //LOG.info("HTTP Status: " + e.getStatus());
            //LOG.info("Error Message: " + e.getMessage());
        }
        return res;

    }


}