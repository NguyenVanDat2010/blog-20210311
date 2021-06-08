package com.kira.blog.controller;

import com.kira.blog.api.LoginApi;
import com.kira.blog.service.LoginService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController implements LoginApi {
    @Resource
    private LoginService loginService;

}
