package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.RepeatPurchaseByTm;
import com.example.backend.entity.TradeStatsByTm;
import com.example.backend.mapper.BrandRepeatMapper;
import com.example.backend.service.BrandOrderService;
import com.example.backend.service.BrandRepeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandRepeatServiceImpl extends ServiceImpl<BrandRepeatMapper, RepeatPurchaseByTm> implements BrandRepeatService {
    @Autowired
    private BrandRepeatMapper brandRepeatMapper;

    @Override
    public List<RepeatPurchaseByTm> getRebuyRate(String dt, String recentDays) {
        return brandRepeatMapper.selectList(new LambdaQueryWrapper<RepeatPurchaseByTm>()
                .eq(RepeatPurchaseByTm::getDt, dt).eq(RepeatPurchaseByTm::getRecentDays, recentDays));
    }
}
