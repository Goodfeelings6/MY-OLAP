package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_user_change")
public class UserChange {
    @TableField("dt")
    private String dt;

    @TableField("user_churn_count")
    private String userChurnCount;

    @TableField("user_back_count")
    private String userBackCount;

}
