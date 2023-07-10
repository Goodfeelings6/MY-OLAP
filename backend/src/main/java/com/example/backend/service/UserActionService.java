package com.example.backend.service;

import com.example.backend.entity.UserAction;

import java.util.List;

public interface UserActionService {
    List<UserAction> getActionDetails(String dt, String recentDays);
}
