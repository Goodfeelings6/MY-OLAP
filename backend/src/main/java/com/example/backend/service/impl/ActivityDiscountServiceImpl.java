package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.ActivityStats;
import com.example.backend.entity.CouponStats;
import com.example.backend.entity.UserChange;
import com.example.backend.mapper.ActivityDiscountMapper;
import com.example.backend.mapper.UserChangeMapper;
import com.example.backend.service.ActivityDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ActivityDiscountServiceImpl extends ServiceImpl<ActivityDiscountMapper, ActivityStats> implements ActivityDiscountService {
    @Autowired
    private ActivityDiscountMapper activityDiscountMapper;

    @Override
    public List<ActivityStats> getDiscountRate(String dt) {
        // 解析日期字符串为LocalDate对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dt, formatter);

        // 进行日期运算
        LocalDate newDate = date.minusDays(7);

        // 格式化日期为字符串
        String newDateString = newDate.format(formatter);

        return activityDiscountMapper.selectList(new LambdaQueryWrapper<ActivityStats>()
                .gt(ActivityStats::getDt, newDateString).le(ActivityStats::getDt, dt));
    }
}
