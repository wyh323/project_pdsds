package com.happy_hao.pdsds.service.impl;

import com.happy_hao.pdsds.common.Result;
import com.happy_hao.pdsds.config.MailConfig;
import com.happy_hao.pdsds.entity.Mail;
import com.happy_hao.pdsds.entity.Patient;
import com.happy_hao.pdsds.exception.ServiceException;
import com.happy_hao.pdsds.mapper.MailMapper;
import com.happy_hao.pdsds.mapper.PatientMapper;
import com.happy_hao.pdsds.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class MailServiceImpl implements EmailService {

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private MailMapper mailMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailUserName;

    @Override
    public Result getCode(String username, String email) {
        // 账号存在校验
        Patient p = patientMapper.findByUsername(username);

        if (p == null) {
            // 没有占用
            // 登录失败
            throw new ServiceException("用户名不存在");
        }

        // 邮箱是否正确
        if (!p.getEmail().equals(email)) {
            throw new ServiceException("邮箱不一致");
        }
        // 检查是否已存在验证码
        Mail mail = mailMapper.findByEmail(email);
        Integer overtime = mailConfig.getOvertime(); // 过期时间
        if (mail == null) {
            // 生成新的验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);

            // 保存到数据库
            mailMapper.add(email, verifyCode);
        } else {
            // 如果验证码已存在，检查是否过期
            if (LocalDateTime.now().isAfter(mail.getCreateTime().plus(overtime, ChronoUnit.MINUTES))) {
                // 生成新的验证码
                String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
                mailMapper.add(email, verifyCode);
            }
        }

        // 编写邮箱内容
        mail = mailMapper.findByEmail(email);
        String verifyCode = mail.getToken();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><head><title></title></head><body>");
        stringBuilder.append("您好:<br/>");
        stringBuilder.append("您的验证码是：").append(verifyCode).append("<br/>");
        stringBuilder.append("您可以复制此验证码并返回至心理疾病辅助诊疗系统找回密码页面，以验证您的邮箱。<br/>");
        stringBuilder.append("此验证码只能使用一次，在");
        stringBuilder.append(overtime.toString());
        stringBuilder.append("分钟内有效。验证成功则自动失效。<br/>");
        stringBuilder.append("如果您没有进行上述操作，请忽略此邮件。");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // 发件配置并发送邮件
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(mailUserName);
            mimeMessageHelper.setTo(email);
            mimeMessage.setSubject("邮箱验证-心理疾病辅助诊疗系统");
            mimeMessageHelper.setText(stringBuilder.toString(), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return Result.success("获取验证码成功，请查看移步您的邮箱" + email + "查看验证码！");
    }
}
