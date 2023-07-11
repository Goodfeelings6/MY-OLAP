package com.example.backend.controller;

import com.example.backend.entity.CouponStats;
import com.example.backend.service.CouponDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coupon/")
@CrossOrigin
public class CouponAnalysisController {
    @Autowired
    private CouponDiscountService couponDiscountService;

    @PostMapping("discount")
    public List<Map<String, String>> discount(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<CouponStats> discountRate = couponDiscountService.getDiscountRate(params.get("dt"));
        for (CouponStats couponStats : discountRate) {
            HashMap<String, String> map = new HashMap<>();
            map.put("coupon_id", couponStats.getCouponId());
            map.put("coupon_name", couponStats.getCouponName());
            map.put("start_date", couponStats.getStartDate());
            map.put("rule_name", couponStats.getRuleName());
            map.put("reduce_rate", couponStats.getReduceRate());
            list.add(map);
        }
        return list;
    }
}
