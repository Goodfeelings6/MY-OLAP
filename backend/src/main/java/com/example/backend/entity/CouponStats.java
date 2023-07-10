package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_activity_stats")
public class CouponStats {
    @TableField("dt")
    private String dt;

    @TableField("activity_id")
    private String activityId;

    @TableField("activity_name")
    private String activityName;

    @TableField("start_date")
    private String startDate;

    @TableField("reduce_rate")
    private String reduceRate;
}
