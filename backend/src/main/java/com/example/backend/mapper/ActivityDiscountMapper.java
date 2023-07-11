package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.ActivityStats;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityDiscountMapper extends BaseMapper<ActivityStats> {
}
