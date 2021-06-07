package com.kira.blog.mapper;

import com.kira.blog.pojo.po.UserPO;

public interface UserMapper {
    int deleteByPrimaryKey(String userUuid);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(String userUuid);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKeyWithBLOBs(UserPO record);

    int updateByPrimaryKey(UserPO record);
}