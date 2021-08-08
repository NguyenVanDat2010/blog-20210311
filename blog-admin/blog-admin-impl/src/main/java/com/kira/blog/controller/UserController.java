package com.kira.blog.controller;

import com.kira.blog.api.UserApi;
import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.ResponseUtils;
import com.kira.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController implements UserApi {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    //dang loi regex
    @Override
    public ResponseBase updateUser(String userUuid, UpdateUserDTO updateUserDTO, String roleRight) {
        logger.info("UserController, updateUser with userUuid is {}", userUuid);
        updateUserDTO.setUserUuid(userUuid);
        userService.updateUser(updateUserDTO, roleRight);
        return ResponseUtils.ok();
    }
}
