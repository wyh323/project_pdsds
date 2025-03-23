package com.happy_hao.pdsds.service;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.dto.PatientLogin;
import com.happy_hao.pdsds.dto.PatientRegister;
import com.happy_hao.pdsds.dto.UpdatePwdRequest;
import com.happy_hao.pdsds.entity.Patient;

public interface PatientService {
    // 根据用户名查询用户
    // Patient findByUsername(String username);

    // 注册
    void register(PatientRegister req);

    // 登录
    Patient login(PatientLogin req);

    Result updatePwd(UpdatePwdRequest updatePwdRequest);

}
