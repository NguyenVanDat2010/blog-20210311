package com.kira.blog.mapper;

import com.kira.blog.pojo.po.CommentPO;

public interface CommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(CommentPO record);

    int insertSelective(CommentPO record);

    CommentPO selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(CommentPO record);

    int updateByPrimaryKeyWithBLOBs(CommentPO record);

    int updateByPrimaryKey(CommentPO record);
}