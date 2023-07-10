package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.UserChange;
import com.example.backend.entity.UserRetention;
import com.example.backend.entity.UserStats;
import com.example.backend.mapper.UserChangeMapper;
import com.example.backend.mapper.UserStatusMapper;
import com.example.backend.service.UserChangeService;
import com.example.backend.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserStatusServiceImpl extends ServiceImpl<UserStatusMapper, UserStats> implements UserStatusService {
    @Autowired
    private UserStatusMapper userStatusMapper;
    @Override
    public List<UserStats> getActiveCount(String dt, String recentDays) {
        // 解析日期字符串为LocalDate对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dt, formatter);

        // 进行日期运算
        LocalDate newDate = date.minusDays(7); // 增加7天

        // 格式化日期为字符串
        String newDateString = newDate.format(formatter);

        return userStatusMapper.selectList(new LambdaQueryWrapper<UserStats>()
                .between(UserStats::getDt, newDateString, dt).eq(UserStats::getRecentDays, recentDays));
    }
}
