package com.lottery.user.service.imlp;

import com.lottery.user.dao.UserMapper;
import com.lottery.user.domain.User;
import com.lottery.user.service.interfaces.IUser.IUserRegister;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason:
 */
@Service("userRegister")
public class UserResgisterImpl implements IUserRegister {
    private static Logger logger = Logger.getLogger(UserResgisterImpl.class);

    private UserMapper userMapper;

    public int insertNewUser(User user) {
        return 0;
    }


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findUser(User user) {
        return  userMapper.selectByPhone(user.getPhoneNum());
    }
}
