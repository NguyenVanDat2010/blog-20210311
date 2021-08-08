package com.kira.blog.api;

import com.kira.blog.pojo.dto.LoginDTO;
import com.kira.blog.pojo.dto.SignUpDTO;
import com.kira.blog.pojo.vo.LoginVO;
import com.kira.blog.pojo.vo.SignUpVO;
import com.kira.blog.response.ResponseBase;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping
public interface LoginApi {

    @ApiOperation("SignUp: User sign up")
    @PostMapping("/signup")
    ResponseBase<SignUpVO> signUp(@RequestBody @Validated SignUpDTO signUpDTO);

    @ApiOperation("Login: User login")
    @PostMapping("/login")
    ResponseBase<LoginVO> login(@RequestBody @Validated LoginDTO loginDTO);

    @ApiOperation("Logout: User logout")
    @PostMapping("/logout")
    ResponseBase logout(@RequestHeader("username") @Validated String username, HttpServletRequest request);

}
