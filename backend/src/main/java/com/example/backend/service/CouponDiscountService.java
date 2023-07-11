package com.example.backend.service;

import com.example.backend.entity.CouponStats;

import java.util.List;

public interface CouponDiscountService {
    List<CouponStats> getDiscountRate(String dt);
}
