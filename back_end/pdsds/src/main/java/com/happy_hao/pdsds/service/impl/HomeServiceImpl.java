package com.happy_hao.pdsds.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy_hao.pdsds.entity.Slide;
import com.happy_hao.pdsds.mapper.HomeMapper;
import com.happy_hao.pdsds.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public List<Slide> getSlides() {
        return homeMapper.findAllSlides();

    }

}
