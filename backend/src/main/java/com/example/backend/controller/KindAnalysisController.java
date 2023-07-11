package com.example.backend.controller;

import com.example.backend.entity.TradeStatsByCate;
import com.example.backend.entity.TradeStatsByTm;
import com.example.backend.service.KindOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/kind/")
public class KindAnalysisController {
    @Autowired
    private KindOrderService kindOrderService;

    @PostMapping("add_order")
    public List<Map<String, String>> addOrder(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<TradeStatsByCate> orderAdd = kindOrderService.getOrderAdd(params.get("dt"),
                params.get("recent_days"));
        for (TradeStatsByCate tradeStatsByCate : orderAdd) {
            HashMap<String, String> map = new HashMap<>();
            map.put("category1_name", tradeStatsByCate.getCategory1Name());
            map.put("category2_name", tradeStatsByCate.getCategory2Name());
            map.put("category3_name", tradeStatsByCate.getCategory3Name());
            map.put("order_count", tradeStatsByCate.getOrderCount());
            map.put("order_user_count", tradeStatsByCate.getOrderUserCount());
            list.add(map);
        }
        return list;
    }
    @PostMapping("reduce_order")
    public List<Map<String, String>> reduceOrder(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<TradeStatsByCate> orderReduce = kindOrderService.getOrderReduce(params.get("dt"),
                params.get("recent_days"));
        for (TradeStatsByCate tradeStatsByCate : orderReduce) {
            HashMap<String, String> map = new HashMap<>();
            map.put("category1_name", tradeStatsByCate.getCategory1Name());
            map.put("category2_name", tradeStatsByCate.getCategory2Name());
            map.put("category3_name", tradeStatsByCate.getCategory3Name());
            map.put("order_refund_count", tradeStatsByCate.getOrderRefundCount());
            map.put("order_refund_user_count", tradeStatsByCate.getOrderRefundUserCount());
            list.add(map);
        }
        return list;
    }

}
