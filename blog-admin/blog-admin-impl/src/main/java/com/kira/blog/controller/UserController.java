package com.kira.blog.controller;

import com.kira.blog.api.UserApi;
import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.pojo.vo.UserManagerVO2;
import com.kira.blog.pojo.vo.UserVO;
import com.kira.blog.pojo.vo.UserVO1;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.ResponseUtils;
import com.kira.blog.response.common.Page;
import com.kira.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController implements UserApi {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Override
    public ResponseBase updateUser(String userUuid, UpdateUserDTO updateUserDTO) {
        logger.info("UserController, updateUser with userUuid is {}", userUuid);
        updateUserDTO.setUserUuid(userUuid);
        userService.updateUser(updateUserDTO);
        return ResponseUtils.ok();
    }

    @Override
    public ResponseBase deleteUser(String userUuid) {
        logger.info("UserController, deleteUser with userUuid is {}", userUuid);
        userService.deleteUserByUserUuid(userUuid);
        return ResponseUtils.ok();
    }

    @Override
    public ResponseBase<UserVO1> getUserByUsername(String username) {
        logger.info("UserController, getUserByUserUuid with username is {}", username);
        UserPO userPO = userService.getUserByUsername(username);
        UserVO1 userVO1 = new UserVO1();
        BeanUtils.copyProperties(userPO, userVO1);
        return ResponseUtils.ok(userVO1);
    }

    @Override
    public ResponseBase<Page<UserManagerVO2>> getListUsers(String roleRight, String pageNo, String pageSize, String startTime, String endTime) {
        logger.info("UserController, getListUsers, roleRight={}, pageNo={}, pageSize={}, startTime={}, endTime={}", roleRight, pageNo, pageSize, startTime, endTime);
        Page<UserManagerVO2> result = userService.listUsers(roleRight, pageNo, pageSize, startTime, endTime);
        return ResponseUtils.ok(result);
    }

    @Override
    public ResponseBase activeUserByUsername(String username) {
        return null;
    }


}
