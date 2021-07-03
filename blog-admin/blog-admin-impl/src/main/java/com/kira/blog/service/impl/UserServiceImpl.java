package com.kira.blog.service.impl;

import com.kira.blog.mapper.UserMapper;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserPO getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
