package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.CouponStats;
import com.example.backend.mapper.CouponDiscountMapper;
import com.example.backend.service.CouponDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CouponDiscountServiceImpl extends ServiceImpl<CouponDiscountMapper, CouponStats> implements CouponDiscountService {
    @Autowired
    private CouponDiscountMapper couponDiscountMapper;

    @Override
    public List<CouponStats> getDiscountRate(String dt) {
        // 解析日期字符串为LocalDate对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dt, formatter);

        // 进行日期运算
        LocalDate newDate = date.minusDays(7);

        // 格式化日期为字符串
        String newDateString = newDate.format(formatter);

        return couponDiscountMapper.selectList(new LambdaQueryWrapper<CouponStats>()
                .gt(CouponStats::getDt, newDateString).le(CouponStats::getDt, dt));
    }
}
