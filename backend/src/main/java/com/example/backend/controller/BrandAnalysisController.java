package com.example.backend.controller;

import com.example.backend.entity.TradeStatsByTm;
import com.example.backend.entity.UserAction;
import com.example.backend.service.BrandOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/brand/")
public class BrandAnalysisController {
    @Autowired
    private BrandOrderService brandOrderService;


    @PostMapping("add_order")
    public List<Map<String, String>> addOrder(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<TradeStatsByTm> orderAdd = brandOrderService.getOrderAdd(params.get("dt"),
                                                        params.get("recent_days"));
        for (TradeStatsByTm tradeStatsByTm : orderAdd) {
            HashMap<String, String> map = new HashMap<>();
            map.put("tm_name", tradeStatsByTm.getTmName());
            map.put("order_count", tradeStatsByTm.getOrderCount());
            map.put("order_user_count", tradeStatsByTm.getOrderUserCount());
            list.add(map);
        }
        return list;
    }

    @PostMapping("reduce_order")
    public List<Map<String, String>> ReduceOrder(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<TradeStatsByTm> orderReduce = brandOrderService.getOrderReduce(params.get("dt"),
                params.get("recent_days"));
        for (TradeStatsByTm tradeStatsByTm : orderReduce) {
            HashMap<String, String> map = new HashMap<>();
            map.put("tm_name", tradeStatsByTm.getTmName());
            map.put("order_refund_count", tradeStatsByTm.getOrderRefundCount());
            map.put("order_refund_user_count", tradeStatsByTm.getOrderRefundUserCount());
            list.add(map);
        }
        return list;
    }
}
