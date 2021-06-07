package com.kira.blog.mapper;

import com.kira.blog.pojo.po.CategoryPO;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insert(CategoryPO record);

    int insertSelective(CategoryPO record);

    CategoryPO selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(CategoryPO record);

    int updateByPrimaryKeyWithBLOBs(CategoryPO record);

    int updateByPrimaryKey(CategoryPO record);
}