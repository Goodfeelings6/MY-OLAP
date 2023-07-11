package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.CouponStats;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponDiscountMapper extends BaseMapper<CouponStats> {
}
