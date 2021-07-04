package com.kira.blog.service.impl;

import com.kira.blog.mapper.UserMapper;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;

    @Override
    public UserPO getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Integer countUserExists(String email, String username, String phoneNumber) {
        logger.info("UserServiceImpl - countUserExists by email-{}, username-{}, phone number-{}", email, username, phoneNumber);
        return userMapper.countUserExists(email, username, phoneNumber);
    }

    @Override
    public int saveUser(UserPO record) {
        logger.info("UserServiceImpl - saveUser with userPO is {}", record);
        return userMapper.insertSelective(record);
    }
}
