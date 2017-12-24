package com.lottery.app.service.interfaces.IUser;

import com.lottery.app.domain.User;

/**
 * Created by gaojunc on 2017/12/24 18:51.
 * Created Reason: 用户接口
 */
public interface IUser {

    /*
        查找用户
     */
    User findUser(User user);

}
