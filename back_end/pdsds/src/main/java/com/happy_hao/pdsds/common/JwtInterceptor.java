package com.happy_hao.pdsds.common;

import com.happy_hao.pdsds.entity.Patient;
import com.happy_hao.pdsds.exception.ServiceException;
import com.happy_hao.pdsds.mapper.PatientMapper;
import com.happy_hao.pdsds.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private PatientMapper patientMapper;

    @Override
    public boolean preHandle(@SuppressWarnings("null") HttpServletRequest request,
            @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") Object handler) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            throw new ServiceException("401", "请登录1");
        }

        String patientName;
        try {
            patientName = JwtUtil.extractClaims(authHeader).get("name", String.class);
        } catch (Exception e) {
            throw new ServiceException("401", "请登录2");
        }

        Patient p = patientMapper.findByUsername(patientName);
        if (p == null) {
            throw new ServiceException("401", "请登录3");
        }

        return true;
    }

}