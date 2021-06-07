package com.kira.blog.mapper;

import com.kira.blog.pojo.po.AccessRightPO;

public interface AccessRightMapper {
    int insert(AccessRightPO record);

    int insertSelective(AccessRightPO record);
}