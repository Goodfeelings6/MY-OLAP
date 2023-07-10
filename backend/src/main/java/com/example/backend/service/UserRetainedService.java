package com.example.backend.service;

import com.example.backend.entity.UserRetention;
import com.example.backend.entity.UserStats;

import java.util.List;

public interface UserRetainedService {
    List<UserRetention> getRetainedRate(String dt);
}
