package com.kira.blog.service.impl;

import com.kira.blog.config.JwtProperty;
import com.kira.blog.mapper.LoginMapper;
import com.kira.blog.pojo.dto.LoginDTO;
import com.kira.blog.pojo.vo.LoginVO;
import com.kira.blog.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Resource
    private LoginMapper loginMapper;

    private static final String forceLoginFlag = "true";

    @Resource
    private JwtProperty jwtProperty;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public void logout(String username, String remoteAddress) {
        String sessionId = stringRedisTemplate.opsForValue().get(username);
        if (!StringUtils.isEmpty(sessionId)) {
            stringRedisTemplate.delete(sessionId);
        }
        stringRedisTemplate.delete(username);
    }
}
