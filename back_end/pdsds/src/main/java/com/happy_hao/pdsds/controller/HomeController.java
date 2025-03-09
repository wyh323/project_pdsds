package com.happy_hao.pdsds.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.service.HomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/slides")
    public Result getSlides() {
        return Result.success(homeService.getSlides());
    }

}
