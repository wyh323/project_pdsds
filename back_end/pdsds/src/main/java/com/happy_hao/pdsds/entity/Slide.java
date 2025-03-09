package com.happy_hao.pdsds.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
public class Slide {
    @NonNull
    private Integer id;// 主键ID
    @NotEmpty
    private String url;// 昵称
}
