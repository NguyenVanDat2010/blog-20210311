package com.kira.blog.service.impl;

import com.kira.blog.mapper.RoleMapper;
import com.kira.blog.pojo.po.RolePO;
import com.kira.blog.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public RolePO selectRoleByPrimaryKey(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
