package com.example.backend.service;

import com.example.backend.entity.TradeStats;

import java.util.List;

public interface OrderService {
    List<TradeStats> getDayOrderDetails(String dt, String recentDays);

    List<TradeStats> getOrderReduce(String dt, String recentDays);
}
