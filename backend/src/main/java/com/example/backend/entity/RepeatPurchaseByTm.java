package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_repeat_purchase_by_tm")
public class RepeatPurchaseByTm {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("tm_id")
    private String tmId;

    @TableField("tm_name")
    private String tmName;
    @TableField("order_repeat_rate")
    private String orderRepeatRate;
}
