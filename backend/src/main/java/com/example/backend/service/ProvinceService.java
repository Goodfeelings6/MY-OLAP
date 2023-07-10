package com.example.backend.service;

import com.example.backend.entity.OrderByProvince;

import java.util.List;

public interface ProvinceService {
    List<OrderByProvince> getProvinceDetails(String dt, String recentDaysd);
}
