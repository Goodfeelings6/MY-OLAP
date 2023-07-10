package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_coupon_stats")
public class ActivityStats {
    @TableField("dt")
    private String dt;

    @TableField("coupon_id")
    private String couponId;

    @TableField("coupon_name")
    private String couponName;

    @TableField("start_date")
    private String startDate;

    @TableField("rule_name")
    private String ruleName;

    @TableField("reduce_rate")
    private String reduceRate;
}
