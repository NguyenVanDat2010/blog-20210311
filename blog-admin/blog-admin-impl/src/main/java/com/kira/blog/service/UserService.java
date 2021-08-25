package com.kira.blog.service;

import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.pojo.vo.UserManagerVO2;
import com.kira.blog.pojo.vo.UserVO;
import com.kira.blog.response.common.Page;


public interface UserService {
    UserPO getUserByUsername(String username);

    int countUserExists(String email, String username, String phoneNumber);

    int saveUser(UserPO record);

    void updateUser(UpdateUserDTO updateUserDTO, String username);

    UserVO getUserByUserUuidOrUsername(String userUuid, String username);

    void deleteUserByUserUuid(String userUuid, String username);

//    List<UserVO> getListUsers(UserListDTO userListDTO);

    Page<UserManagerVO2> listUsers(String roleRight, String pageNo, String pageSize, String startTime, String endTime);

}
