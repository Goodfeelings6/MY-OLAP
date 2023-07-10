package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_user_retention")
public class UserRetention {
    @TableField("dt")
    private String dt;

    @TableField("create_date")
    private String createDate;

    @TableField("retention_day")
    private String retentionDay;

    @TableField("retention_count")
    private String retentionCount;

    @TableField("newUserCount")
    private String avgDurationSec;

    @TableField("retention_rate")
    private String retentionRate;
}
