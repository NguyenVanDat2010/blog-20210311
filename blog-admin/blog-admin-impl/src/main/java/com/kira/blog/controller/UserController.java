package com.kira.blog.controller;

import com.kira.blog.api.UserApi;
import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.vo.UserVO;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.ResponseUtils;
import com.kira.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController implements UserApi {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Override
    public ResponseBase updateUser(String userUuid, UpdateUserDTO updateUserDTO, String roleRight) {
        logger.info("UserController, updateUser with userUuid is {}", userUuid);
        updateUserDTO.setUserUuid(userUuid);
        userService.updateUser(updateUserDTO, roleRight);
        return ResponseUtils.ok();
    }

    @Override
    public ResponseBase deleteUser(String userUuid) {
        logger.info("UserController, deleteUser with userUuid is {}", userUuid);
        userService.deleteUserByUserUuid(userUuid);
        return ResponseUtils.ok();
    }

    @Override
    public ResponseBase<UserVO> getUserByUserUuid(String userUuid) {
        logger.info("UserController, getUserByUserUuid with userUuid is {}", userUuid);
        UserVO userVO = userService.getUserByUserUuidOrUsername(userUuid, null);
        userVO.setPassword(null);
        return ResponseUtils.ok(userVO);
    }

    @Override
    public ResponseBase<List<UserVO>> getListUsers() {
        return null;
    }


}
