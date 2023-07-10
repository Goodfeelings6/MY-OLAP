package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_new_buyer_stats")
public class NewBuyerStats {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("new_order_user_count")
    private String newOrderUserCount;

    @TableField("new_payment_user_count")
    private String newPaymentUserCount;
}
