package com.example.backend.controller;

import com.example.backend.entity.OrderByProvince;
import com.example.backend.entity.TradeStatsByCate;
import com.example.backend.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/province")
public class ProvinceAnalysisController {
    @Autowired
    private ProvinceService provinceService;

    @PostMapping("/")
    public List<Map<String, String>> addOrder(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<OrderByProvince> provinceDetails = provinceService.getProvinceDetails(params.get("dt"),
                params.get("recent_days"));
        for (OrderByProvince orderByProvince : provinceDetails) {
            HashMap<String, String> map = new HashMap<>();
            map.put("province_name", orderByProvince.getProvinceName());
            map.put("order_count", orderByProvince.getOrderCount());
            map.put("order_total_amount", orderByProvince.getOrderTotalAmount());
            list.add(map);
        }
        return list;
    }
}
