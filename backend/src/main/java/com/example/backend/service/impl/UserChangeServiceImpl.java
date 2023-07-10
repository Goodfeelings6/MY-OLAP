package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.entity.UserChange;
import com.example.backend.mapper.UserChangeMapper;
import com.example.backend.service.UserChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserChangeServiceImpl extends ServiceImpl<UserChangeMapper, UserChange> implements UserChangeService {
    @Autowired
    private UserChangeMapper userChangeMapper;


    @Override
    public List<UserChange> getFlowUser(String dt) {
        // 解析日期字符串为LocalDate对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dt, formatter);

        // 进行日期运算
        LocalDate newDate = date.minusDays(7); // 增加7天

        // 格式化日期为字符串
        String newDateString = newDate.format(formatter);

        return userChangeMapper.selectList(new LambdaQueryWrapper<UserChange>()
                .between(UserChange::getDt, newDateString, dt));
    }

}
