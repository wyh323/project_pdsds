package com.happy_hao.pdsds.service;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.dto.LoginReq;
import com.happy_hao.pdsds.dto.RegisterReq;
import com.happy_hao.pdsds.dto.UpdatePwdRequest;
import com.happy_hao.pdsds.entity.User;
import com.happy_hao.pdsds.mapper.UserMapper;

public interface UserService {

    <T> void register(RegisterReq req, UserMapper<T> userMapper);

    <T extends User> T login(LoginReq req, UserMapper<T> userMapper);

    <T> Result updatePwd(UpdatePwdRequest updatePwdRequest, UserMapper<T> userMapper);
}
