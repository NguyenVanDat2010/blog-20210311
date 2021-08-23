package com.kira.blog.service;

import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.dto.UserListDTO;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.pojo.vo.UserManagerVO2;
import com.kira.blog.pojo.vo.UserVO;
import com.kira.blog.response.common.Page;

import java.util.List;

public interface UserService {
    UserPO getUserByUsername(String username);

    Integer countUserExists(String email, String username, String phoneNumber);

    int saveUser(UserPO record);

    void updateUser(UpdateUserDTO updateUserDTO, String roleRight);

    UserVO getUserByUserUuidOrUsername(String userUuid, String username);

    void deleteUserByUserUuid(String userUuid);

//    List<UserVO> getListUsers(UserListDTO userListDTO);

    Page<UserManagerVO2> listUsers(String pageNo, String pageSize, String startTime, String endTime);

}
