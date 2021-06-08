package com.kira.blog.controller;

import com.kira.blog.api.UserApi;
import com.kira.blog.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController implements UserApi {
    @Resource
    private UserService userService;

}
