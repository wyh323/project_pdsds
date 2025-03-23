package com.happy_hao.pdsds.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.config.MailConfig;
import com.happy_hao.pdsds.dto.PatientLogin;
import com.happy_hao.pdsds.dto.PatientRegister;
import com.happy_hao.pdsds.dto.UpdatePwdRequest;
import com.happy_hao.pdsds.entity.Mail;
import com.happy_hao.pdsds.entity.Patient;
import com.happy_hao.pdsds.exception.ServiceException;
import com.happy_hao.pdsds.mapper.MailMapper;
import com.happy_hao.pdsds.mapper.PatientMapper;
import com.happy_hao.pdsds.service.PatientService;
import com.happy_hao.pdsds.utils.JwtUtil;
import com.happy_hao.pdsds.utils.Md5Util;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private MailMapper mailMapper;

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private JwtUtil jwtUtil;

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
        String jString = jwtUtil.generateToken(claims);
        p.setToken(jString);

        return p;
    }

    @Override
    public Result updatePwd(UpdatePwdRequest updatePwdRequest) {
        String username = updatePwdRequest.getUsername();
        String email = updatePwdRequest.getEmail();
        String token = updatePwdRequest.getToken();
        String newPwd = updatePwdRequest.getNewPwd();

        // 账号存在校验
        Patient p = patientMapper.findByUsername(username);

        if (p == null) {
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
        patientMapper.updatePwdByUserName(username, Md5Util.getMD5String(newPwd));

        // 将验证码过期
        mailMapper.updateCreateTimeByToken(token, Instant.EPOCH.atZone(ZoneId.systemDefault()).toLocalDateTime());

        return Result.success("密码重置成功！请牢记您的密码！");
    }
}
