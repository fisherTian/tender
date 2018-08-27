package com.ziyu.tender.tender.mapper;

import com.ziyu.tender.tender.model.Account;


public interface AccountMapper {
    int insert(Account record);

    int insertSelective(Account record);
}