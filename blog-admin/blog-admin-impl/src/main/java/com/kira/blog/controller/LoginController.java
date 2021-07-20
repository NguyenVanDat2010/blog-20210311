package com.kira.blog.controller;

import com.kira.blog.api.LoginApi;
import com.kira.blog.pojo.dto.LoginDTO;
import com.kira.blog.pojo.dto.SignUpDTO;
import com.kira.blog.pojo.vo.LoginVO;
import com.kira.blog.pojo.vo.SignUpVO;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.ResponseUtils;
import com.kira.blog.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class LoginController implements LoginApi {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LoginService loginService;

    @Override
    public ResponseBase<SignUpVO> signUp (SignUpDTO signUpDTO){
        logger.info("LoginController, sign up with username is {}", signUpDTO.getUsername());
        SignUpVO signUpVO = loginService.signUp(signUpDTO);
        return ResponseUtils.ok(signUpVO);
    }

    @Override
    public ResponseBase<LoginVO> login(LoginDTO loginDTO) {
        logger.info("LoginController, login with username is {}", loginDTO.getUsername());
        LoginVO loginVO = loginService.login(loginDTO);
        return ResponseUtils.ok(loginVO);
    }

    @Override
    public ResponseBase logout(String username, HttpServletRequest request) {
        logger.info("LoginController, logout with username is {}", username);
        String remoteAddress = request.getHeader("remoteAddr");
        loginService.logout(username, remoteAddress);
        return ResponseUtils.ok();
    }
}
