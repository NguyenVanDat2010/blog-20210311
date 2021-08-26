package com.kira.blog.controller;

import com.kira.blog.api.RoleApi;
import com.kira.blog.pojo.dto.RoleDTO;
import com.kira.blog.pojo.po.RolePO;
import com.kira.blog.pojo.vo.UserManagerVO2;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.ResponseUtils;
import com.kira.blog.response.common.Page;
import com.kira.blog.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author datnv33
 */
@RestController
public class RoleController implements RoleApi {
    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;

    @Override
    public ResponseBase createRole(String username, String roleRight, RoleDTO roleDTO) {
        logger.info("RoleController, User-{}, createRole with roleRight-{}, roleDTO-{}", username, roleRight, roleDTO);
        roleService.createRole(username, roleRight, roleDTO);
        return ResponseUtils.ok();
    }

    @Override
    public ResponseBase updateRole(String username, String roleRight, RoleDTO roleDTO) {
        logger.info("RoleController, User-{}, updateRole with roleRight-{}, roleDTO-{}", username, roleRight, roleDTO);
        roleService.updateRole(username, roleRight, roleDTO);
        return ResponseUtils.ok();
    }

    @Override
    public ResponseBase<RolePO> getRoleById(Long roleId, String roleRight) {
        logger.info("RoleController, getRoleById with id-{}, roleRight-{}", roleId, roleRight);
        RolePO rolePO = roleService.selectRoleByPrimaryKey(roleId, roleRight);
        return ResponseUtils.ok(rolePO);
    }

    @Override
    public ResponseBase deleteRoleById(Long roleId, String roleRight) {
        logger.info("RoleController, deleteRoleById with id-{}, roleRight-{}", roleId, roleRight);
        roleService.deleteRoleById(roleId, roleRight);
        return ResponseUtils.ok();
    }

    @Override
    public ResponseBase<Page<RolePO>> getListRoles(String roleRight, String pageNo, String pageSize, String startTime, String endTime) {
        logger.info("RoleController, getListRoles with roleRight-{}", roleRight);
        Page<RolePO> result = roleService.getListRoles(roleRight, pageNo, pageSize, startTime, endTime);
        return ResponseUtils.ok(result);
    }
}
