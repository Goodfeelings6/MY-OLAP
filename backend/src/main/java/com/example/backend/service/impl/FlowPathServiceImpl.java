package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.CouponStats;
import com.example.backend.entity.PagePath;
import com.example.backend.entity.TrafficStatusChannel;
import com.example.backend.mapper.CouponDiscountMapper;
import com.example.backend.mapper.FlowPathMapper;
import com.example.backend.service.FlowPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowPathServiceImpl extends ServiceImpl<FlowPathMapper, PagePath> implements FlowPathService {
    @Autowired
    private FlowPathMapper flowPathMapper;

    @Override
    public List<PagePath> getFlowPath(String dt, String recentDays) {
        return flowPathMapper.selectList(new LambdaQueryWrapper<PagePath>()
                .eq(PagePath::getDt, dt).eq(PagePath::getRecentDays, recentDays));
    }
}
