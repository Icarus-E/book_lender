package com.piggy.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_merge_book")
public class UserBookRelation {
    private Integer id;
    @TableField("user_id")
    private Integer userId;
    @TableField("book_id")
    private Integer bookId;
}
