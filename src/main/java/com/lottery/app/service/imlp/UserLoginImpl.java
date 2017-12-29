package com.lottery.app.service.imlp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lottery.app.dao.UserMapper;
import com.lottery.app.domain.Model.ResultStatus;
import com.lottery.app.domain.User;
import com.lottery.app.service.interfaces.IUser.IUserLogin;
import com.lottery.app.util.ErrorCodes;
import com.lottery.app.util.SuccessCodes;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Created by gaojunc on 2017/12/24 17:06.
 * Created Reason:用户登录默认实现类
 */
@Service("userLogin")
public class UserLoginImpl implements IUserLogin {

    private UserMapper userMapper;

    /*
     * 用户登录
     */
    public String login(String requestBody) throws UnsupportedEncodingException {

        if (requestBody != null && requestBody.length() > 0) {
            JSONArray objects = JSON.parseArray(requestBody);
            String phone = objects.getString(0);
            String password = objects.getString(1);
            String type = objects.getString(3);

            User user = new User();
            ResultStatus resultStatus = new ResultStatus();
            user.setPhoneNum(phone);

            //使用验证码登录
            if (type.equalsIgnoreCase("verify_code")) {

                //使用手机、密码登录
            } else if (type.equalsIgnoreCase("phone")) {
                User rst = findUser(user);
                if (!rst.getPassword().equals(password)){
                        resultStatus.setStatusCode(ErrorCodes.INPUT_ERROR);
                        resultStatus.setStatusInfo("用户密码输入错误!");
                    }else {
                        resultStatus.setStatusCode(SuccessCodes.ACTION_SUCCESSFULLY);
                        resultStatus.setStatusInfo("登录成功!");
                    }
                }
            return JSON.toJSONString(resultStatus);
        }
        else {
            return null;
        }
    }

    public String loginWithVerifyCode(String phoneNum, String verifyCode, String failTime) {
        return null;
    }

    public String loginWithPassword(String account, String password) {
        return null;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findUser(User user) {
        return userMapper.selectByPhone(user.getPhoneNum());
    }
}
