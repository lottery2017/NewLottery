package com.lottery.account.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lottery.user.dao.UserMapper;
import com.lottery.user.domain.Model.ResultStatus;
import com.lottery.user.domain.User;
import com.lottery.util.IdentifyCardValidateUtil;
import com.lottery.util.RequestUtil;
import com.lottery.util.exception.ResponeseCodes;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by gaojunc on 2018/1/16 17:01.
 * Created Reason:用户绑定身份证
 */
@Service(value = "realNameAuth")
public class AccountAuthService {
    private UserMapper userMapper;
    private static Logger log = Logger.getLogger(AccountAuthService.class);

    public String doAuth(HttpServletRequest request) {
        String body = null;
        ResultStatus resultStatus = new ResultStatus();
        try {
            body = RequestUtil._getRequestBody(request);
            JSONObject jsonObject = JSON.parseObject(body);
            String nickname = jsonObject.getString("nickname");
            String real_name = jsonObject.getString("real_name");
            String identifycard_num = jsonObject.getString("identifycard_num");
            User user = userMapper.selectByNickName(nickname);
            String info = IdentifyCardValidateUtil.IDCardValidate(identifycard_num);
            user.setUsername(real_name);
            user.setIdentifyCode(identifycard_num);
            if (IdentifyCardValidateUtil.getvalidTag() && (userMapper.updateByPrimaryKeySelective(user) == 1)) {
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000001);
                resultStatus.setStatusInfo("实名认证成功");
            } else {
                resultStatus.setStatusCode(ResponeseCodes.CODE_E000009);
                resultStatus.setStatusInfo("实名认证失败。" + info);
            }
        } catch (IOException e) {
            if (log.isEnabledFor(Priority.ERROR))
                log.error("获取请求报文失败。", e);
            resultStatus.setStatusCode(ResponeseCodes.CODE_E000005);
            resultStatus.setStatusInfo("获取请求报文失败。");
            return JSON.toJSONString(resultStatus);
        }
        return JSON.toJSONString(resultStatus);
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
