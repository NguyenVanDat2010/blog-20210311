package com.kira.blog.mapper;

import com.kira.blog.pojo.po.PostPO;
import com.kira.blog.pojo.po.PostPOWithBLOBs;

public interface PostMapper {
    int deleteByPrimaryKey(Long postId);

    int insert(PostPOWithBLOBs record);

    int insertSelective(PostPOWithBLOBs record);

    PostPOWithBLOBs selectByPrimaryKey(Long postId);

    int updateByPrimaryKeySelective(PostPOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PostPOWithBLOBs record);

    int updateByPrimaryKey(PostPO record);
}