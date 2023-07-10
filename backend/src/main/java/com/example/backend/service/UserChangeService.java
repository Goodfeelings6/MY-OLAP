package com.example.backend.service;

import com.example.backend.entity.UserChange;

import java.util.List;

public interface UserChangeService {
    List<UserChange> getFlowUser(String dt);

}
