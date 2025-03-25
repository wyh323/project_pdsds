package com.happy_hao.pdsds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    protected Integer id; // 主键ID

    protected String username; // 用户名

    @JsonIgnore
    protected String password; // 密码

    protected String nickname; // 昵称

    @Email
    protected String email; // 邮箱

    protected String pic; // 头像地址

    protected String token; // JWT令牌
}
