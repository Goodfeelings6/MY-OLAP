package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "ads_traffic_stats_by_channel")
public class TrafficStatusChannel {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("channel")
    private String channel;

    @TableField("uv_count")
    private String uvCount;

    @TableField("avg_duration_sec")
    private String avgDurationSec;

    @TableField("avg_page_count")
    private String avgPageCount;

    @TableField("sv_count")
    private String svCount;

    @TableField("bounce_rate")
    private String bounceRate;
}
