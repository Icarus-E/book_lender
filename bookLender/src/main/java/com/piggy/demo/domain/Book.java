package com.piggy.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String name;
    private String type;
    private String description;
    @TableField(exist = false)
    private boolean isBorrowed = false;
}
