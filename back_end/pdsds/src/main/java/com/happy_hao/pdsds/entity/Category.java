package com.happy_hao.pdsds.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = Update.class) // 注：不是：@NonNull
    private Integer id;// 主键ID
    @NotEmpty // 未定义归属分组，则属于Default分组
    private String categoryName;// 分类名称
    @NotEmpty // 未定义归属分组，则属于Default分组
    private String categoryAlias;// 分类别名
    private Integer createUser;// 创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;// 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;// 更新时间

    // 在没有指定分组的情况下，会使用默认分组进行校验。
    // 分组之间还可以通过继承关系来实现校验规则的复用。
    // 比如A extends B，A继承B，那么A中拥有B中所有的
    // 校验项。
    // Add分组继承Default分组，它拥有Default分组
    // 的所有校验项。同样Update也拥有Default分组的所
    // 有校验项。
    public interface Add extends Default {
    }

    public interface Update extends Default {
    }
}
