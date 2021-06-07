package com.kira.blog.mapper;

import com.kira.blog.pojo.po.UserEvenLogPO;
import com.kira.blog.pojo.po.UserEvenLogPOWithBLOBs;

public interface UserEvenLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(UserEvenLogPOWithBLOBs record);

    int insertSelective(UserEvenLogPOWithBLOBs record);

    UserEvenLogPOWithBLOBs selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(UserEvenLogPOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserEvenLogPOWithBLOBs record);

    int updateByPrimaryKey(UserEvenLogPO record);
}