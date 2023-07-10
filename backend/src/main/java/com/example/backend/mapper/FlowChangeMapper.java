package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.TrafficStatusChannel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FlowChangeMapper extends BaseMapper<TrafficStatusChannel> {

}
