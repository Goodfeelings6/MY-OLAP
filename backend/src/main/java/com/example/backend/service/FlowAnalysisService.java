package com.example.backend.service;

import com.example.backend.entity.TrafficStatusChannel;

import java.util.List;

public interface FlowAnalysisService {
    List<TrafficStatusChannel> getCustNum(String dt, String recentDays);

    List<TrafficStatusChannel> getSessionDetail(String dt, String recentDays);

    List<TrafficStatusChannel> getJumpRate(String dt, String recentDays);
}
