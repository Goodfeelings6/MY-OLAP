package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.TradeStatsByCate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KindOrderMapper extends BaseMapper<TradeStatsByCate> {
}
