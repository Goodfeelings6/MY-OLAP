package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_trade_stats_by_cate")
public class TradeStatsByCate {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("category1_id")
    private String category1Id;

    @TableField("category1_name")
    private String category1Name;

    @TableField("category2_id")
    private String category2Id;

    @TableField("category2_name")
    private String category2Name;

    @TableField("category3_id")
    private String category3Id;

    @TableField("category3_name")
    private String category3Name;

    @TableField("order_count")
    private String orderCount;

    @TableField("order_user_count")
    private String orderUserCount;

    @TableField("order_refund_count")
    private String orderRefundCount;

    @TableField("order_refund_user_count")
    private String orderRefundUserCount;
}
