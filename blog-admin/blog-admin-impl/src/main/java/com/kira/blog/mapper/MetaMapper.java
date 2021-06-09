package com.kira.blog.mapper;

import com.kira.blog.pojo.po.MetaPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MetaMapper {
    int deleteByPrimaryKey(Long metaId);

    int insert(MetaPO record);

    int insertSelective(MetaPO record);

    MetaPO selectByPrimaryKey(Long metaId);

    int updateByPrimaryKeySelective(MetaPO record);

    int updateByPrimaryKeyWithBLOBs(MetaPO record);

    int updateByPrimaryKey(MetaPO record);
}