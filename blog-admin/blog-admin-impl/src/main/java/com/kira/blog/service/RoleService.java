package com.kira.blog.service;

import com.kira.blog.pojo.dto.RoleDTO;
import com.kira.blog.pojo.po.RolePO;
import com.kira.blog.response.common.Page;

public interface RoleService {

    RolePO selectRoleByPrimaryKey(Long roleId, String roleRight);

    RolePO selectRoleByRoleRight(String roleRight);

    void createRole(String username, String roleRight, RoleDTO roleDTO);

    void updateRole(String username, String roleRight, RoleDTO roleDTO);

    void deleteRoleById(Long roleId, String roleRight);

    Page<RolePO> getListRoles(String roleRight, String pageNo, String pageSize, String startTime, String endTime);

}
