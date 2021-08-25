package com.kira.blog.service.impl;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.exception.BizException;
import com.kira.blog.mapper.RoleMapper;
import com.kira.blog.pojo.dto.RoleDTO;
import com.kira.blog.pojo.po.RolePO;
import com.kira.blog.service.RoleService;
import com.kira.blog.utils.RoleValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author datnv33
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private RoleMapper roleMapper;

    @Override
    public RolePO selectRoleByPrimaryKey(Long roleId) {
        logger.info("RoleServiceImpl, selectRoleByPrimaryKey - roleId is {}", roleId);
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public RolePO selectRoleByRoleRight(String roleRight) {
        logger.info("RoleServiceImpl, selectRoleByRoleRight - roleRight is {}", roleRight);
        return roleMapper.selectRoleByRoleRight(roleRight);
    }

    @Override
    public void createRole(String username, String roleRight, RoleDTO roleDTO) {
        logger.info("RoleServiceImpl, User-{}, createRole with roleRight-{}, roleDTO-{}", username, roleRight, roleDTO);
        boolean withCheck = RoleValidateUtil.roleSADNeedCheck(roleRight);
        if (!withCheck) {
            throw new BizException(ExceptionEnum.USER_HAVE_NO_PERMISSION);
        }
        RolePO rolePO = new RolePO();
        BeanUtils.copyProperties(roleDTO, rolePO);
        if (!StringUtils.isEmpty(username)) {
            rolePO.setUpdatedBy(username);
            rolePO.setCreatedBy(username);
        }
        roleMapper.insertSelective(rolePO);
        logger.info("RoleServiceImpl, User-{} createRole successfully!", username);
    }
}
