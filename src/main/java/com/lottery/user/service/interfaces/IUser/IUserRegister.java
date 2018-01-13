package com.lottery.user.service.interfaces.IUser;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason: 注册用户
 */
public interface IUserRegister extends IUser{

    /**
     * 使用验证码注册新用户
     * @return
     */
    String registerWithVerifyCode(String request);

}
