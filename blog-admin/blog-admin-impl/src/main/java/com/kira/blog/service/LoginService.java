package com.kira.blog.service;

import com.kira.blog.pojo.dto.LoginDTO;
import com.kira.blog.pojo.dto.SignUpDTO;
import com.kira.blog.pojo.vo.LoginVO;
import com.kira.blog.pojo.vo.SignUpVO;

public interface LoginService {

    SignUpVO signUp (SignUpDTO signUpDTO);

    LoginVO login(LoginDTO loginDTO);

    void logout(String username, String remoteAddress);
}
