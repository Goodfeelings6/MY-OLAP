package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_trade_stats")
public class TradeStats {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("order_total_amount")
    private String orderTotalAmount;

    @TableField("order_count")
    private String orderCount;

    @TableField("order_user_count")
    private String orderUserCount;

    @TableField("order_refund_count")
    private String orderRefundCount;

    @TableField("order_refund_user_count")
    private String orderRefundUserCount;
}
