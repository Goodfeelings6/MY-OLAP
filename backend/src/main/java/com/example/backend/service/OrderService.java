package com.example.backend.service;

import com.example.backend.entity.TradeStats;

import java.util.List;

public interface OrderService {
    List<TradeStats> getOrderDetails(String dt, String recentDays);
}
