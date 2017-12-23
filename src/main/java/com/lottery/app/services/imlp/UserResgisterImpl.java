package com.lottery.app.services.imlp;

import com.lottery.app.dao.UserMapper;
import com.lottery.app.domain.User;
import com.lottery.app.domain.UserExample;
import com.lottery.app.services.interfaces.IUserRegister;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by gaojunc on 2017/12/21.
 * Created Reason:
 */
@Service("registerService")
public class UserResgisterImpl implements IUserRegister {
    private static Logger logger = Logger.getLogger(UserResgisterImpl.class);

    @Resource
    private UserMapper userMapper;

    public int insertNewUser(User user) {
        if (logger.isDebugEnabled())
            logger.debug("开始插入新用户信息...");
        int rst;
        if (user != null)
            rst = userMapper.insert(user);
        else rst = 0;
        return rst;
    }

    public int countByExamlpe(UserExample example) {
        return userMapper.countByExample(example);
    }

}
