package com.kira.blog.service;

import com.kira.blog.pojo.dto.RoleDTO;
import com.kira.blog.pojo.po.RolePO;

public interface RoleService {
    RolePO selectRoleByPrimaryKey(Long roleId);

    RolePO selectRoleByRoleRight(String roleRight);

    void createRole(String username, String roleRight, RoleDTO roleDTO);
}
