package com.lottery.app.service.interfaces.IUser;

import com.lottery.app.domain.User;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason: 注册用户
 */
public interface IUserRegister{

    /**
     * 注册新用户
     * @param user
     * @return
     */
    int insertNewUser(User user);
}
