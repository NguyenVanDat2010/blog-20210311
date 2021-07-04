package com.kira.blog.service;

import com.kira.blog.pojo.po.UserPO;

public interface UserService {
    UserPO getUserByUsername(String username);

    Integer countUserExists(String email, String username, String phoneNumber);

    int saveUser(UserPO record);
}
