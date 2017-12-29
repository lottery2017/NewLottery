package com.lottery.app.service.imlp;

import com.lottery.app.dao.UserMapper;
import com.lottery.app.domain.User;
import com.lottery.app.service.interfaces.IUser.IUserRegister;
import com.lottery.app.util.ErrorCodes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason:
 */
@Service("userService")
public class UserResgisterImpl implements IUserRegister {
    private static Logger logger = Logger.getLogger(UserResgisterImpl.class);

    private UserMapper userMapper;

    public int insertNewUser(User user) {
        if (logger.isDebugEnabled())
            logger.debug("开始插入新用户信息...");

        if (findUser(user) != null)
            return ErrorCodes.USER_ALLREADY_EXITS;

        int rst;
        if (user != null)
            rst = userMapper.insert(user);
        else rst = ErrorCodes.DATABASE_UNNORMAL;
        return rst;
    }


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findUser(User user) {
        return  userMapper.selectByPhone(user.getPhoneNum());
    }
}
