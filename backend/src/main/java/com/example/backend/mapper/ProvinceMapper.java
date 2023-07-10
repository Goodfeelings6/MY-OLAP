package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.OrderByProvince;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceMapper extends BaseMapper<OrderByProvince> {
}
