package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.UserRetention;
import org.apache.ibatis.annotations.Mapper;
import org.codehaus.jackson.map.Serializers;

@Mapper
public interface UserRetainedMapper extends BaseMapper<UserRetention> {
}
