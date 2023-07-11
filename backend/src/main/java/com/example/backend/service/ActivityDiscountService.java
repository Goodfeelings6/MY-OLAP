package com.example.backend.service;

import com.example.backend.entity.ActivityStats;

import java.util.List;

public interface ActivityDiscountService {
    List<ActivityStats> getDiscountRate(String dt);
}
