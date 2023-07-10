package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.TradeStatsByTm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandOrderMapper extends BaseMapper<TradeStatsByTm> {
}
