package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.UserAction;
import com.example.backend.entity.UserRetention;
import com.example.backend.entity.UserStats;
import com.example.backend.mapper.UserActionMapper;
import com.example.backend.mapper.UserStatusMapper;
import com.example.backend.service.UserActionService;
import com.example.backend.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActionServiceImpl extends ServiceImpl<UserActionMapper, UserAction> implements UserActionService {
    @Autowired
    private UserActionMapper userActionMapper;
    @Override
    public List<UserAction> getActionDetails(String dt, String recentDays) {
        return userActionMapper.selectList(new LambdaQueryWrapper<UserAction>()
                .eq(UserAction::getDt, dt).eq(UserAction::getRecentDays, recentDays));
    }
}
