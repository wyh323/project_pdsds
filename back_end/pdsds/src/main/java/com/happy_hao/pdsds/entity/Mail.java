package com.happy_hao.pdsds.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Mail {
    @NonNull
    private Integer id;// 主键ID
    @NotEmpty
    @Email
    private String email;// 邮箱
    @JsonIgnore
    private String token;// 邮箱验证码
    private LocalDateTime createTime;
}
