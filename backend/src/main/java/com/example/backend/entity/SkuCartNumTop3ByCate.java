package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_sku_cart_num_top3_by_cate")
public class SkuCartNumTop3ByCate {
    @TableField("dt")
    private String dt;

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

    @TableField("sku_id")
    private String skuId;
    @TableField("sku_name")
    private String skuName;

    @TableField("cart_num")
    private String cartNum;

    @TableField("rk")
    private String rk;

}
