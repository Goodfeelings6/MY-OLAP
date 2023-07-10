package com.example.backend.service;

import com.example.backend.entity.UserAction;
import com.example.backend.entity.UserStats;

import java.util.List;

public interface UserStatusService {
    List<UserStats> getActiveCount(String dt, String recentDays);

}
