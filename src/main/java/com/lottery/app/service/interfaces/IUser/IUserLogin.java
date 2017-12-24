package com.lottery.app.service.interfaces.IUser;

/**
 * Created by gaojunc on 2017/12/24 17:02.
 * Created Reason: 用户登录接口
 */
public interface IUserLogin extends IUser{

    /**
     * 用户登录
     * @param requestBody
     * @return 响应报文
     */
    String login(String requestBody);

    /**
     * 使用手机号验证码登录
     * @param phoneNum 手机号
     * @param verifyCode 验证码
     * @param failTime 超时时间
     * @return 响应报文
     */
    String loginWithVerifyCode(String phoneNum, String verifyCode, String failTime);

    /**
     * 使用账号密码登录
     * @param account 账号
     * @param password 密码（sha256）
     * @return 响应报文
     */
    String loginWithPassword(String account, String password);
}
