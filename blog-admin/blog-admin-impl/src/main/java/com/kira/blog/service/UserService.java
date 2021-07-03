package com.kira.blog.service;

import com.kira.blog.pojo.po.UserPO;

public interface UserService {
    UserPO getUserByUsername(String username);
}
