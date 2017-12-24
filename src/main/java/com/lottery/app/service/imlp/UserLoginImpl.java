package com.lottery.app.service.imlp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lottery.app.dao.UserMapper;
import com.lottery.app.domain.User;
import com.lottery.app.domain.UserExample;
import com.lottery.app.service.interfaces.IUser.IUserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gaojunc on 2017/12/24 17:06.
 * Created Reason:用户登录默认实现类
 */
@Service("userLogin")
public class UserLoginImpl implements IUserLogin {

    private UserMapper userMapper;

    public String login(String requestBody) {

        if (requestBody != null && requestBody.length() > 0) {
            JSONArray objects = JSON.parseArray(requestBody);
            String phone = objects.getString(0);
            String password = objects.getString(1);
            String type = objects.getString(3);

        }
        return null;
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
        return null;
    }
}
