package com.kira.blog.mapper;

import com.kira.blog.pojo.po.AccessRightPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessRightMapper {
    int insert(AccessRightPO record);

    int insertSelective(AccessRightPO record);
}