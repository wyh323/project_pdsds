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

        // 获取JWT令牌
        String authHeader = request.getHeader("Authorization");

        // 拦截没有JWT令牌的请求
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServiceException("401", "请登录1");
        }

        // 去掉Bearer
        authHeader = authHeader.substring(7);

        // 拦截签名不正确的请求
        String patientName;
        try {
            patientName = JwtUtil.extractClaims(authHeader).get("username", String.class);
        } catch (Exception e) {
            throw new ServiceException("401", "请登录2");
        }

        // 拦截不存在的令牌
        Patient p = patientMapper.findByUsername(patientName);
        if (p == null) {
            throw new ServiceException("401", "请登录3");
        }

        return true;
    }

}