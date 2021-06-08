package com.kira.blog.api;

import com.kira.blog.pojo.dto.LoginDTO;
import com.kira.blog.pojo.vo.LoginVO;
import com.kira.blog.response.ResponseBase;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

//@RequestMapping
public interface LoginApi {

    @ApiOperation("Login: User login")
    @PostMapping("/login")
    ResponseBase<LoginVO> login(@Validated @RequestBody LoginDTO loginDTO);

    @ApiOperation("Logout: User logout")
    @PostMapping("/logout")
    ResponseBase logout(@Validated @RequestHeader("username") String username, HttpServletRequest request);

}
