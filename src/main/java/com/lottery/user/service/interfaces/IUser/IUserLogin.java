package com.lottery.user.service.interfaces.IUser;

import java.io.IOException;

/**
 * Created by gaojunc on 2017/12/24 17:02.
 * Created Reason: 用户登录接口
 */
public interface IUserLogin extends IUser{

    /**
     * 使用手机号验证码登录
     * @return 响应报文
     */
    String loginWithVerifyCode(String requestBody) throws IOException;

    /**
     * 使用账号密码登录
     * @return 响应报文
     */
    String loginWithPassword(String requestBody);
}
