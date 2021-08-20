package com.kira.blog.mapper;

import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String userUuid);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(String userUuid);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKeyWithBLOBs(UserPO record);

    int updateByPrimaryKey(UserPO record);

    UserPO getUserByUsername(String username);

    Integer countUserExists(@Param("email") String email, @Param("username") String username, @Param("phoneNumber") String phoneNumber);

    UserVO getUserByUserUuidOrUsername(@Param("userUuid") String userUuid, @Param("username") String username);

    int deleteUserByUserUuid(@Param("userUuid") String userUuid);

    List<UserVO> getListUsers();
}