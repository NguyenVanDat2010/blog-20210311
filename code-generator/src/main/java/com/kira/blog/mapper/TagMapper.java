package com.kira.blog.mapper;

import com.kira.blog.pojo.po.TagPO;

public interface TagMapper {
    int deleteByPrimaryKey(Long tagId);

    int insert(TagPO record);

    int insertSelective(TagPO record);

    TagPO selectByPrimaryKey(Long tagId);

    int updateByPrimaryKeySelective(TagPO record);

    int updateByPrimaryKeyWithBLOBs(TagPO record);

    int updateByPrimaryKey(TagPO record);
}