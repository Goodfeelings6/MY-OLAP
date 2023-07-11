package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.PagePath;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FlowPathMapper extends BaseMapper<PagePath> {
}
