package com.happy_hao.pdsds.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper<T> {
    T findByUsername(String username);

    void add(String username, String password);

    void updatePwdByUserName(String username, String newPassword);
}
