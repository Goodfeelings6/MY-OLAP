package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.RepeatPurchaseByTm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandRepeatMapper extends BaseMapper<RepeatPurchaseByTm> {
}
