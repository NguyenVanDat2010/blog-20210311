package com.kira.blog.service.impl;

import com.kira.blog.mapper.RoleMapper;
import com.kira.blog.pojo.po.RolePO;
import com.kira.blog.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private RoleMapper roleMapper;

    @Override
    public RolePO selectRoleByPrimaryKey(Long roleId) {
        logger.info("selectRoleByPrimaryKey - roleId is {}", roleId);
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public RolePO selectRoleByRoleRight(String roleRight) {
        logger.info("selectRoleByRoleRight - roleRight is {}", roleRight);
        return roleMapper.selectRoleByRoleRight(roleRight);
    }
}
