package com.happy_hao.pdsds.controller;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.dto.EmailRequest;
import com.happy_hao.pdsds.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService mailService;

    @GetMapping("/getCode")
    public Result getCode(@Valid @RequestBody EmailRequest req) {
        return mailService.getCode(req.getUsername(), req.getEmail());
    }
}
