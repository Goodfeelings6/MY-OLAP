package com.example.backend.controller;

import com.example.backend.entity.ActivityStats;
import com.example.backend.entity.CouponStats;
import com.example.backend.service.ActivityDiscountService;
import com.example.backend.service.CouponDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity/")
@CrossOrigin
public class ActivityAnalysisController {
    @Autowired
    private ActivityDiscountService activityDiscountService;

    @PostMapping("discount")
    public List<Map<String, String>> discount(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<ActivityStats> discountRate = activityDiscountService.getDiscountRate(params.get("dt"));
        for (ActivityStats activityStats : discountRate) {
            HashMap<String, String> map = new HashMap<>();
            map.put("activity_id", activityStats.getActivityId());
            map.put("activity_name", activityStats.getActivityName());
            map.put("start_date", activityStats.getStartDate());
            map.put("reduce_rate", activityStats.getReduceRate());
            list.add(map);
        }
        return list;
    }
}
