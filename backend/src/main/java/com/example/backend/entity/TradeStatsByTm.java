package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_trade_stats_by_tm")
public class TradeStatsByTm {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("tm_id")
    private String tmId;

    @TableField("tm_name")
    private String tmName;
    @TableField("order_count")
    private String orderCount;

    @TableField("orderUserCount")
    private String orderUserCount;

    @TableField("order_refund_count")
    private String orderRefundCount;

    @TableField("order_refund_user_count")
    private String orderRefundUserCount;
}
