package com.kira.blog.service;

import com.kira.blog.pojo.dto.LoginDTO;
import com.kira.blog.pojo.vo.LoginVO;

public interface LoginService {
    LoginVO login(LoginDTO loginDTO);

    void logout(String username, String remoteAddress);
}
