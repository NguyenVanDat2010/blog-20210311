package com.kira.blog.service;

import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.pojo.vo.UserManagerVO2;
import com.kira.blog.pojo.vo.UserVO;
import com.kira.blog.response.common.Page;


public interface UserService {
    UserPO getUserByUsername(String username);

    int countUserExists(String email, String username, String phoneNumber);

    void saveUser(UserPO record, boolean isInsert);

    void updateUser(UpdateUserDTO updateUserDTO, String username, String roleRight);

    UserPO getUserByUserUuid(String userUuid, String username, String roleRight);

    UserVO getUserByUserUuidOrUsername(String userUuid, String username);

    void deleteUserByUserUuid(String userUuid, String username, String roleRight);

//    List<UserVO> getListUsers(UserListDTO userListDTO);

    Page<UserManagerVO2> listUsers(String roleRight, String pageNo, String pageSize, String startTime, String endTime);

}
