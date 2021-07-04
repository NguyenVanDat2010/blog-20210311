package com.kira.blog.service;

import com.kira.blog.pojo.po.RolePO;

public interface RoleService {
    RolePO selectRoleByPrimaryKey(Long roleId);

    RolePO selectRoleByRoleRight(String roleRight);

}
