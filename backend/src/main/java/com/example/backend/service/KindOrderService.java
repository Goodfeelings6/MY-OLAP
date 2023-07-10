package com.example.backend.service;

import com.example.backend.entity.TradeStatsByCate;

import java.util.List;

public interface KindOrderService {
    List<TradeStatsByCate> getOrderAdd(String dt, String recentDays);

    List<TradeStatsByCate> getOrderReduce(String dt, String recentDays);
}
