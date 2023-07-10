package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_user_stats")
public class UserStats {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("new_user_count")
    private String newUserCount;

    @TableField("active_user_count")
    private String activeUserCount;
}
