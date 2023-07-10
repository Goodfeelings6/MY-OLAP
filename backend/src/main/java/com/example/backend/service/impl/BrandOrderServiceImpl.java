package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.NewBuyerStats;
import com.example.backend.entity.TradeStatsByTm;
import com.example.backend.mapper.BrandOrderMapper;
import com.example.backend.service.BrandOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandOrderServiceImpl extends ServiceImpl<BrandOrderMapper, TradeStatsByTm> implements BrandOrderService {
    @Autowired
    private BrandOrderMapper brandOrderMapper;

    @Override
    public List<TradeStatsByTm> getOrderAdd(String dt, String recentDays) {
        return brandOrderMapper.selectList(new LambdaQueryWrapper<TradeStatsByTm>()
                .eq(TradeStatsByTm::getDt, dt).eq(TradeStatsByTm::getRecentDays, recentDays));
    }

    @Override
    public List<TradeStatsByTm> getOrderReduce(String dt, String recentDays) {
        return brandOrderMapper.selectList(new LambdaQueryWrapper<TradeStatsByTm>()
                .eq(TradeStatsByTm::getDt, dt).eq(TradeStatsByTm::getRecentDays, recentDays));
    }
}
