package com.happy_hao.pdsds.service.impl;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.config.MailConfig;
import com.happy_hao.pdsds.dto.LoginReq;
import com.happy_hao.pdsds.dto.RegisterReq;
import com.happy_hao.pdsds.dto.UpdatePwdRequest;
import com.happy_hao.pdsds.entity.Mail;
import com.happy_hao.pdsds.entity.User;
import com.happy_hao.pdsds.exception.ServiceException;
import com.happy_hao.pdsds.mapper.MailMapper;
import com.happy_hao.pdsds.mapper.UserMapper;
import com.happy_hao.pdsds.service.UserService;
import com.happy_hao.pdsds.utils.JwtUtil;
import com.happy_hao.pdsds.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MailMapper mailMapper;

    @Autowired
    private MailConfig mailConfig;

    @Override
    public <T> void register(RegisterReq req, UserMapper<T> userMapper) {
        String username = req.getUsername();
        String password = req.getPassword();

        // 查询用户是否存在
        T user = userMapper.findByUsername(username);
        if (user != null) {
            throw new ServiceException("用户名已被注册");
        }

        // 加密密码
        String mdString = Md5Util.getMD5String(password);

        // 添加用户
        userMapper.add(username, mdString);
    }

    @Override
    public <T extends User> T login(LoginReq req, UserMapper<T> userMapper) {
        String username = req.getUsername();
        String password = req.getPassword();

        // 查询用户是否存在
        T user = userMapper.findByUsername(username);
        if (user == null) {
            throw new ServiceException("用户名或密码错误");
        }

        // 密码是否正确
        if (!user.getPassword().equals(Md5Util.getMD5String(password))) {
            throw new ServiceException("用户名或密码错误");
        }

        // 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        String identity = user.getClass().getSimpleName().toLowerCase();
        claims.put("identity", identity);
        String jwtToken = jwtUtil.generateToken(claims);
        user.setToken(jwtToken);

        return user;
    }

    @Override
    public <T> Result updatePwd(UpdatePwdRequest updatePwdRequest, UserMapper<T> userMapper) {
        String username = updatePwdRequest.getUsername();
        String email = updatePwdRequest.getEmail();
        String token = updatePwdRequest.getToken();
        String newPwd = updatePwdRequest.getNewPwd();

        // 账号存在校验
        T user = userMapper.findByUsername(username);

        if (user == null) {
            // 没有占用
            // 登录失败
            throw new ServiceException("用户名错误");
        }
        Mail mail = mailMapper.findByEmail(email);
        Integer overtime = mailConfig.getOvertime(); // 过期时间
        // 验证码过期校验
        if (LocalDateTime.now().isAfter(mail.getCreateTime().plusMinutes(overtime))) {
            throw new ServiceException("验证码已过期");
        }
        // 验证码正确性校验
        if (!mail.getToken().equals(token)) {
            throw new ServiceException("验证码错误");
        }

        // 修改密码
        userMapper.updatePwdByUserName(username, Md5Util.getMD5String(newPwd));

        // 将验证码过期
        mailMapper.updateCreateTimeByToken(token, Instant.EPOCH.atZone(ZoneId.systemDefault()).toLocalDateTime());

        return Result.success("密码重置成功！请牢记您的密码！");
    }

}
