package com.happy_hao.pdsds.service.impl;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.dto.LoginReq;
import com.happy_hao.pdsds.dto.RegisterReq;
import com.happy_hao.pdsds.dto.UpdatePwdRequest;
import com.happy_hao.pdsds.entity.Doctor;
import com.happy_hao.pdsds.mapper.DoctorMapper;
import com.happy_hao.pdsds.service.DoctorService;
import com.happy_hao.pdsds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private UserService userService;


    @Override
    public void register(RegisterReq req) {
        userService.register(req, doctorMapper);
    }

    @Override
    public Doctor login(LoginReq req) {
        return userService.login(req, doctorMapper);
    }

    @Override
    public Result updatePwd(UpdatePwdRequest updatePwdRequest) {
        return userService.updatePwd(updatePwdRequest, doctorMapper);
    }
}
