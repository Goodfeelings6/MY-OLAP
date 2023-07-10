package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_user_action")
public class UserAction {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("home_count")
    private String homeCount;

    @TableField("good_detail_count")
    private String goodDetailCount;
    @TableField("cart_count")
    private String cartCount;

    @TableField("order_count")
    private String orderCount;

    @TableField("payment_count")
    private String paymentCount;
}
