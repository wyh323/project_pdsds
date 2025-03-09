package com.happy_hao.pdsds.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.happy_hao.pdsds.dto.PatientLogin;
import com.happy_hao.pdsds.dto.PatientRegister;
import com.happy_hao.pdsds.entity.Patient;
import com.happy_hao.pdsds.exception.ServiceException;
import com.happy_hao.pdsds.mapper.PatientMapper;
import com.happy_hao.pdsds.service.PatientService;
import com.happy_hao.pdsds.utils.JwtUtil;
import com.happy_hao.pdsds.utils.Md5Util;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Patient findByUsername(String username) {
        Patient p = patientMapper.findByUsername(username);
        return p;
    }

    @Override
    public void register(PatientRegister req) {
        String username = req.getUsername();
        String password = req.getPassword();

        // 查询病人是否存在
        Patient p = patientMapper.findByUsername(username);
        if (p != null) {
            // 占用
            throw new ServiceException("用户名已被注册");
        }

        // 加密
        String mdString = Md5Util.getMD5String(password);
        // 添加
        patientMapper.add(username, mdString);
    }

    @Override
    public Patient login(PatientLogin req) {
        String username = req.getUsername();
        String password = req.getPassword();

        // 查询病人是否存在
        Patient p = patientMapper.findByUsername(username);

        if (p == null) {
            // 没有占用
            // 登录失败
            throw new ServiceException("用户名或密码错误");
        }

        // 密码是否正确
        if (!p.getPassword().equals(Md5Util.getMD5String(password))) {
            throw new ServiceException("用户名或密码错误");
        }

        // JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", p.getUsername());
        String jString = JwtUtil.generateToken(claims);
        p.setToken(jString);

        return p;
    }

}
