package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_order_by_province")
public class OrderByProvince {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("province_id")
    private String provinceId;

    @TableField("province_name")
    private String provinceName;

    @TableField("area_code")
    private String areaCode;

    @TableField("iso_code")
    private String isoCode;

    @TableField("iso_code_3166_2")
    private String isoCode31662;

    @TableField("order_count")
    private String orderCount;

    @TableField("order_total_amount")
    private String orderTotalAmount;
}
