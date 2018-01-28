package com.lottery.account.dao;

import com.lottery.account.domain.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(String userid);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}