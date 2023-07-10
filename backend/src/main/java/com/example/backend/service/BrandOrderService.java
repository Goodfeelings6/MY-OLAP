package com.example.backend.service;

import com.example.backend.entity.TradeStatsByTm;

import java.util.List;

public interface BrandOrderService {
    List<TradeStatsByTm> getOrderAdd(String dt, String recentDays);

    List<TradeStatsByTm> getOrderReduce(String dt, String recentDays);
}
