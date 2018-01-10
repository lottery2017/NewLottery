package com.lottery.user.dao;

import com.lottery.user.domain.UserVerifycode;

public interface UserVerifycodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserVerifycode record);

    int insertSelective(UserVerifycode record);

    UserVerifycode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserVerifycode record);

    int updateByPrimaryKey(UserVerifycode record);

    UserVerifycode selectByPhone(String phoneNum);
}