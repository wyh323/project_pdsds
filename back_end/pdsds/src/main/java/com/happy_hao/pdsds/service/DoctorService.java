package com.happy_hao.pdsds.service;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.dto.LoginReq;
import com.happy_hao.pdsds.dto.RegisterReq;
import com.happy_hao.pdsds.dto.UpdatePwdRequest;
import com.happy_hao.pdsds.entity.Doctor;
import jakarta.validation.Valid;

public interface DoctorService {
    void register(@Valid RegisterReq req);

    Doctor login(@Valid LoginReq req);

    Result updatePwd(@Valid UpdatePwdRequest updatePwdRequest);
}
