package com.lottery.app.services.interfaces;

import com.lottery.app.domain.User;
import com.lottery.app.domain.UserExample;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason:
 */
public interface IUserRegister {
    int insertNewUser(User user);

    int countByExamlpe(UserExample example);
}
