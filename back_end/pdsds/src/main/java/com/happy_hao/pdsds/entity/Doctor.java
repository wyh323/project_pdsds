package com.happy_hao.pdsds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
public class Doctor {
    @NonNull
    private Integer id;// 主键ID
    private String username;// 用户名
    @JsonIgnore
    private String password;// 密码
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;// 昵称
    @NotEmpty
    @Email
    private String email;// 邮箱
    private String pic;// 头像地址
    private String address;
    private String token;// JWT令牌
}
