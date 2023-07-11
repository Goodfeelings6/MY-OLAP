package com.example.backend.service;

import com.example.backend.entity.PagePath;

import java.util.List;

public interface FlowPathService {
    List<PagePath> getFlowPath(String dt, String recentDays);
}
