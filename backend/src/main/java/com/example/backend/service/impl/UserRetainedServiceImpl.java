package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.UserChange;
import com.example.backend.entity.UserRetention;
import com.example.backend.entity.UserStats;
import com.example.backend.mapper.UserChangeMapper;
import com.example.backend.mapper.UserRetainedMapper;
import com.example.backend.service.UserChangeService;
import com.example.backend.service.UserRetainedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserRetainedServiceImpl extends ServiceImpl<UserRetainedMapper, UserRetention> implements UserRetainedService {
    @Autowired
    private UserRetainedMapper userRetainedMapper;
    @Override
    public List<UserRetention> getRetainedRate(String dt) {
        // 解析日期字符串为LocalDate对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dt, formatter);

        // 进行日期运算
        LocalDate newDate = date.minusDays(7); // 增加7天

        // 格式化日期为字符串
        String newDateString = newDate.format(formatter);

        return userRetainedMapper.selectList(new LambdaQueryWrapper<UserRetention>()
                .between(UserRetention::getDt, newDateString, dt));
    }
}
