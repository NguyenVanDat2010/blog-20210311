package com.kira.blog.mapper;

import com.kira.blog.pojo.po.ModulePO;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer moduleId);

    int insert(ModulePO record);

    int insertSelective(ModulePO record);

    ModulePO selectByPrimaryKey(Integer moduleId);

    int updateByPrimaryKeySelective(ModulePO record);

    int updateByPrimaryKey(ModulePO record);
}