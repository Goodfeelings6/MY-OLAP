package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.TradeStatsByCate;
import com.example.backend.mapper.KindOrderMapper;
import com.example.backend.service.KindOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindOrderServiceImpl extends ServiceImpl<KindOrderMapper, TradeStatsByCate> implements KindOrderService {
    @Autowired
    private KindOrderMapper kindOrderMapper;

    @Override
    public List<TradeStatsByCate> getOrderAdd(String dt, String recentDays) {
        return kindOrderMapper.selectList(new LambdaQueryWrapper<TradeStatsByCate>().eq(TradeStatsByCate::getDt, dt)
                .eq(TradeStatsByCate::getRecentDays, recentDays));
    }

    @Override
    public List<TradeStatsByCate> getOrderReduce(String dt, String recentDays) {
        return kindOrderMapper.selectList(new LambdaQueryWrapper<TradeStatsByCate>().eq(TradeStatsByCate::getDt, dt)
                .eq(TradeStatsByCate::getRecentDays, recentDays));
    }
}
