package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.UserStats;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserStatusMapper extends BaseMapper<UserStats> {
}
