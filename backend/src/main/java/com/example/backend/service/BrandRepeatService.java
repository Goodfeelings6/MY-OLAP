package com.example.backend.service;

import com.example.backend.entity.RepeatPurchaseByTm;

import java.util.List;

public interface BrandRepeatService {
    List<RepeatPurchaseByTm> getRebuyRate(String dt, String recentDays);
}
