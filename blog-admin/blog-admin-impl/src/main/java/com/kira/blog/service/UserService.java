package com.kira.blog.service;

import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.pojo.vo.UserVO;

import java.util.List;

public interface UserService {
    UserPO getUserByUsername(String username);

    Integer countUserExists(String email, String username, String phoneNumber);

    int saveUser(UserPO record);

    void updateUser(UpdateUserDTO updateUserDTO, String roleRight);

    UserVO getUserByUserUuidOrUsername(String userUuid, String username);

    void deleteUserByUserUuid(String userUuid);

    List<UserVO> getListUsers();
}
