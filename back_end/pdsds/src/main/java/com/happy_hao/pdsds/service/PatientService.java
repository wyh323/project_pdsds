package com.happy_hao.pdsds.service;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.dto.LoginReq;
import com.happy_hao.pdsds.dto.RegisterReq;
import com.happy_hao.pdsds.dto.UpdatePwdRequest;
import com.happy_hao.pdsds.entity.Patient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public interface PatientService {
    // 根据用户名查询用户
    // Patient findByUsername(String username);

    // 注册
    void register(@Valid RegisterReq req);

    // 登录
    Patient login(@Valid LoginReq req);

    Result updatePwd(@Valid UpdatePwdRequest updatePwdRequest);

}
