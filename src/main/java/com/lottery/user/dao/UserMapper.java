package com.lottery.user.dao;

import com.lottery.user.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    User selectByPhone(String phoneNum);

    User selectByNickName(String nickName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}