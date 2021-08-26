package com.kira.blog.mapper;

import com.kira.blog.pojo.dto.ListQueryDTO;
import com.kira.blog.pojo.po.RolePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(RolePO record);

    int insertSelective(RolePO record);

    RolePO selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(RolePO record);

    int updateByPrimaryKey(RolePO record);

    RolePO selectRoleByRoleRight(@Param("roleRight") String roleRight);

    int countRoles(ListQueryDTO listQueryDTO);

    List<RolePO> getListRoles(ListQueryDTO listQueryDTO);

}