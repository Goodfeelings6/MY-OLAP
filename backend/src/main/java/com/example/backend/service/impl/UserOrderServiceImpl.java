package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.NewBuyerStats;
import com.example.backend.entity.UserAction;
import com.example.backend.mapper.UserActionMapper;
import com.example.backend.mapper.UserOrderMapper;
import com.example.backend.service.UserActionService;
import com.example.backend.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, NewBuyerStats> implements UserOrderService {
    @Autowired
    private UserOrderMapper userOrderMapper;

    @Override
    public List<NewBuyerStats> getOrderDetails(String dt, String recentDays) {
        return userOrderMapper.selectList(new LambdaQueryWrapper<NewBuyerStats>()
                .eq(NewBuyerStats::getDt, dt).eq(NewBuyerStats::getRecentDays, recentDays));
    }
}
