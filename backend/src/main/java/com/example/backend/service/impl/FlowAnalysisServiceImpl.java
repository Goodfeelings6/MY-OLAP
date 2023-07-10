package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.TrafficStatusChannel;
import com.example.backend.mapper.FlowChangeMapper;
import com.example.backend.service.FlowAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowAnalysisServiceImpl extends ServiceImpl<FlowChangeMapper, TrafficStatusChannel> implements FlowAnalysisService {

    @Autowired
    private FlowChangeMapper flowChangeMapper;
    @Override
    public List<TrafficStatusChannel> getCustNum(String dt, String recentDays) {
        return flowChangeMapper.selectList(new LambdaQueryWrapper<TrafficStatusChannel>()
                .eq(TrafficStatusChannel::getDt, dt).eq(TrafficStatusChannel::getRecentDays, recentDays));
    }

    @Override
    public List<TrafficStatusChannel> getSessionDetail(String dt, String recentDays) {
        return flowChangeMapper.selectList(new LambdaQueryWrapper<TrafficStatusChannel>()
                .eq(TrafficStatusChannel::getDt, dt).eq(TrafficStatusChannel::getRecentDays, recentDays));
    }

    @Override
    public List<TrafficStatusChannel> getJumpRate(String dt, String recentDays) {
        return flowChangeMapper.selectList(new LambdaQueryWrapper<TrafficStatusChannel>()
                .eq(TrafficStatusChannel::getDt, dt).eq(TrafficStatusChannel::getRecentDays, recentDays));
    }
}
