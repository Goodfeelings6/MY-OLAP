package com.example.backend.controller;

import com.example.backend.entity.PagePath;
import com.example.backend.entity.TrafficStatusChannel;
import com.example.backend.service.FlowAnalysisService;
import com.example.backend.service.FlowPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/flow/")
public class FlowAnalysisController {
    @Autowired
    private FlowAnalysisService flowAnalysisService;
    @Autowired
    private FlowPathService flowPathService;

    @PostMapping("cust_num")
    public List<Map<String, String>> custNum(@RequestBody Map<String, String> params) {
//        Map<String, String> params = new HashMap<>();
//        params.put("dt", "2020-06-14");
//        params.put("recent_days", "1");
        List<Map<String, String>> list = new ArrayList<>();
        List<TrafficStatusChannel> channelNum = flowAnalysisService.getCustNum(params.get("dt"), params.get("recent_days"));
        for (TrafficStatusChannel trafficStatusChannel : channelNum) {
            HashMap<String, String> map = new HashMap<>();
            map.put("channel", trafficStatusChannel.getChannel());
            map.put("uv_count", trafficStatusChannel.getUvCount());
            list.add(map);
        }
        return list;
    }

    @PostMapping("session")
    public List<Map<String, String>> session(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<TrafficStatusChannel> sessionDetail = flowAnalysisService.getSessionDetail(params.get("dt"), params.get("recent_days"));
        for (TrafficStatusChannel trafficStatusChannel : sessionDetail) {
            HashMap<String, String> map = new HashMap<>();
            map.put("channel", trafficStatusChannel.getChannel());
            map.put("sv_count", trafficStatusChannel.getSvCount());
            map.put("avg_page_count", trafficStatusChannel.getAvgPageCount());
            map.put("avg_duration_sec", trafficStatusChannel.getAvgDurationSec());
            list.add(map);
        }
        return list;
    }

    @PostMapping("jump")
    public List<Map<String, String>> jump(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<TrafficStatusChannel> jumpRate = flowAnalysisService.getJumpRate(params.get("dt"), params.get("recent_days"));
        for (TrafficStatusChannel trafficStatusChannel : jumpRate) {
            HashMap<String, String> map = new HashMap<>();
            map.put("channel", trafficStatusChannel.getChannel());
            map.put("bounce_rate", trafficStatusChannel.getBounceRate());
            list.add(map);
        }
        return list;
    }

    @PostMapping("path")
    public List<Map<String, String>> path(@RequestBody Map<String, String> params) {
        List<Map<String, String>> list = new ArrayList<>();
        List<PagePath> flowPath = flowPathService.getFlowPath(params.get("dt"), params.get("recent_days"));
        for (PagePath pagePath : flowPath) {
            HashMap<String, String> map = new HashMap<>();
            map.put("source", pagePath.getSource());
            map.put("target", pagePath.getTarget());
            map.put("path_count", pagePath.getPathCount());
            list.add(map);
        }
        return list;
    }
}
