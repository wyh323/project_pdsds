package com.happy_hao.pdsds.service.impl;

import com.happy_hao.pdsds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.dto.LoginReq;
import com.happy_hao.pdsds.dto.RegisterReq;
import com.happy_hao.pdsds.dto.UpdatePwdRequest;
import com.happy_hao.pdsds.entity.Patient;
import com.happy_hao.pdsds.mapper.PatientMapper;
import com.happy_hao.pdsds.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private UserService userService;


    @Override
    public void register(RegisterReq req) {
        userService.register(req, patientMapper);
    }

    @Override
    public Patient login(LoginReq req) {
        return userService.login(req, patientMapper);
    }

    @Override
    public Result updatePwd(UpdatePwdRequest updatePwdRequest) {
        return userService.updatePwd(updatePwdRequest, patientMapper);
    }
}
