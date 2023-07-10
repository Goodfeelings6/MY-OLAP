package com.example.backend.service;

import com.example.backend.entity.NewBuyerStats;

import java.util.List;

public interface UserOrderService {
    List<NewBuyerStats> getOrderDetails(String dt, String recentDays);
}
