package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.OrderByProvince;
import com.example.backend.entity.TradeStats;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, TradeStats> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<TradeStats> getOrderDetails(String dt, String recentDays) {
        return orderMapper.selectList(new LambdaQueryWrapper<TradeStats>().eq(TradeStats::getDt, dt)
                .eq(TradeStats::getRecentDays, recentDays));
    }

}
