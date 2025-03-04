package com.happy_hao.pdsds.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.dto.PatientLogin;
import com.happy_hao.pdsds.dto.PatientRegister;
import com.happy_hao.pdsds.entity.Patient;
import com.happy_hao.pdsds.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register")
    public Result register(@Valid @RequestBody PatientRegister req) {

        patientService.register(req);

        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@Valid @RequestBody PatientLogin req) {

        Patient p = patientService.login(req);

        return Result.success(p.getToken());
    }

}
