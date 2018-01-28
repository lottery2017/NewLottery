package com.lottery.account.dao;

import com.lottery.account.domain.AccountRecord;

public interface AccountRecordMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(AccountRecord record);

    int insertSelective(AccountRecord record);

    AccountRecord selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(AccountRecord record);

    int updateByPrimaryKey(AccountRecord record);
}