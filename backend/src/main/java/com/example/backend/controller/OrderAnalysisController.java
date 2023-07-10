package com.example.backend.controller;

import com.example.backend.entity.OrderByProvince;
import com.example.backend.entity.TradeStats;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderAnalysisController {
    @Autowired
    private OrderService orderService;

    @PostMapping("day")
    public List<Map<String, String>> day(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<TradeStats> dayOrderDetails = orderService.getDayOrderDetails(params.get("dt"),
                params.get("recent_days"));
        for (TradeStats tradeStats : dayOrderDetails) {
            HashMap<String, String> map = new HashMap<>();
            map.put("order_total_amount", tradeStats.getOrderTotalAmount());
            map.put("order_count", tradeStats.getOrderCount());
            list.add(map);
        }
        return list;
    }

    @PostMapping("reduce")
    public List<Map<String, String>> reduce(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<TradeStats> orderReduce = orderService.getOrderReduce(params.get("dt"),
                params.get("recent_days"));
        for (TradeStats tradeStats : orderReduce) {
            HashMap<String, String> map = new HashMap<>();
            map.put("order_refund_user_count", tradeStats.getOrderRefundUserCount());
            list.add(map);
        }
        return list;
    }
}
