package com.happy_hao.pdsds.common;

import com.happy_hao.pdsds.entity.User;
import com.happy_hao.pdsds.exception.ServiceException;
import com.happy_hao.pdsds.mapper.DoctorMapper;
import com.happy_hao.pdsds.mapper.PatientMapper;
import com.happy_hao.pdsds.mapper.UserMapper;
import com.happy_hao.pdsds.utils.JwtUtil;
import jakarta.annotation.Nonnull;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @Nonnull HttpServletResponse response, @Nonnull Object handler) {

        // 获取JWT令牌
        String authHeader = request.getHeader("Authorization");

        // 拦截没有JWT令牌的请求
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServiceException("401", "请登录1");
        }

        // 去掉Bearer
        authHeader = authHeader.substring(7);

        // 拦截签名不正确的请求
        Map<String, Object> claims;
        try {
            claims = jwtUtil.extractClaims(authHeader);
        } catch (Exception e) {
            throw new ServiceException("401", "请登录2");
        }

        // 获取用户名和用户类型
        String username = (String) claims.get("username");
        String identity = (String) claims.get("identity");

        // 根据用户类型选择对应的 UserMapper 实现
        UserMapper<? extends User> userMapper;
        if ("doctor".equals(identity)) {
            userMapper = applicationContext.getBean(DoctorMapper.class);
        } else if ("patient".equals(identity)) {
            userMapper = applicationContext.getBean(PatientMapper.class);
        } else {
            throw new ServiceException("401", "未知用户类型");
        }

        // 查询用户是否存在
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new ServiceException("401", "请登录3");
        }

        // 将用户信息存入请求属性中，供后续使用
        request.setAttribute("user", user);

        return true;
    }
}