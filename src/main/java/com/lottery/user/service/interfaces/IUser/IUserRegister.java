package com.lottery.user.service.interfaces.IUser;

import com.lottery.user.domain.User;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason: 注册用户
 */
public interface IUserRegister extends IUser{

    /**
     * 注册新用户
     * @param user
     * @return
     */
    int insertNewUser(User user);
}
