package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.OrderByProvince;
import com.example.backend.entity.TradeStatsByCate;
import com.example.backend.mapper.KindOrderMapper;
import com.example.backend.mapper.ProvinceMapper;
import com.example.backend.service.KindOrderService;
import com.example.backend.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, OrderByProvince> implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<OrderByProvince> getProvinceDetails(String dt, String recentDays) {
        return provinceMapper.selectList(new LambdaQueryWrapper<OrderByProvince>().eq(OrderByProvince::getDt, dt)
                .eq(OrderByProvince::getRecentDays, recentDays));
    }
}
