package com.example.backend.controller;

import com.example.backend.entity.*;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user/")
public class UserAnalysisController {
    @Autowired
    private UserChangeService userChangeService;
    @Autowired
    private UserRetainedService userRetainedService;
    @Autowired
    private UserStatusService userStatusService;
    @Autowired
    private UserActionService userActionService;
    @Autowired
    private UserOrderService userOrderService;

    @PostMapping("change")
    public List<Map<String, String>> change(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<UserChange> flowUser = userChangeService.getFlowUser(params.get("dt"));
        for (UserChange userChange : flowUser) {
            HashMap<String, String> map = new HashMap<>();
            map.put("dt", userChange.getDt());
            map.put("user_churn_count", userChange.getUserChurnCount());
            map.put("user_back_count", userChange.getUserBackCount());
            list.add(map);
        }
        return list;
    }

    @PostMapping("retained")
    public List<Map<String, String>> retained(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<UserRetention> retainedRate = userRetainedService.getRetainedRate(params.get("dt"));
        for (UserRetention userRetention : retainedRate) {
            HashMap<String, String> map = new HashMap<>();
            map.put("dt", userRetention.getDt());
            map.put("retention_rate", userRetention.getRetentionRate());
            list.add(map);
        }
        return list;
    }

    @PostMapping("active")
    public List<Map<String, String>> active(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<UserStats> activeCount = userStatusService.getActiveCount(params.get("dt"), params.get("recent_days"));
        for (UserStats userStats : activeCount) {
            HashMap<String, String> map = new HashMap<>();
            map.put("dt", userStats.getDt());
            map.put("new_user_count", userStats.getNewUserCount());
            map.put("active_user_count", userStats.getActiveUserCount());
            list.add(map);
        }
        return list;
    }

    @PostMapping("action")
    public List<Map<String, String>> action(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<UserAction> actionDetails = userActionService.getActionDetails(params.get("dt"), params.get("recent_days"));
        for (UserAction userAction : actionDetails) {
            HashMap<String, String> map = new HashMap<>();
            map.put("home_count", userAction.getHomeCount());
            map.put("good_detail_count", userAction.getGoodDetailCount());
            map.put("cart_count", userAction.getCartCount());
            map.put("order_count", userAction.getOrderCount());
            map.put("payment_count", userAction.getPaymentCount());
            list.add(map);
        }
        return list;
    }

    @PostMapping("order")
    public List<Map<String, String>> order(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<NewBuyerStats> orderDetails = userOrderService.getOrderDetails(params.get("dt"), params.get("recent_days"));
        for (NewBuyerStats newBuyerStats : orderDetails) {
            HashMap<String, String> map = new HashMap<>();
            map.put("dt", newBuyerStats.getDt());
            map.put("new_order_user_count", newBuyerStats.getNewOrderUserCount());
            map.put("new_payment_user_count", newBuyerStats.getNewPaymentUserCount());
            list.add(map);
        }
        return list;
    }
}
