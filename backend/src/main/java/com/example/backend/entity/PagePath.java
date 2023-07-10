package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_page_path")
public class PagePath {
    @TableField("dt")
    private String dt;

    @TableField("recent_days")
    private String recentDays;

    @TableField("source")
    private String source;

    @TableField("target")
    private String target;

    @TableField("path_count")
    private String pathCount;
}
