package com.happy_hao.pdsds.controller;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.dto.LoginReq;
import com.happy_hao.pdsds.dto.RegisterReq;
import com.happy_hao.pdsds.dto.UpdatePwdRequest;
import com.happy_hao.pdsds.entity.Doctor;
import com.happy_hao.pdsds.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register")
    public Result register(@Valid @RequestBody RegisterReq req) {

        doctorService.register(req);

        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@Valid @RequestBody LoginReq req) {

        Doctor d = doctorService.login(req);

        return Result.success(d.getToken());
    }

    @PostMapping("/updatePwd")
    public Result updatePwd(@Valid @RequestBody UpdatePwdRequest updatePwdRequest) {
        return doctorService.updatePwd(updatePwdRequest);
    }
}
