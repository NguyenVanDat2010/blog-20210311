package com.kira.blog.service.impl;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.constant.GlobalConst;
import com.kira.blog.constant.RoleConst;
import com.kira.blog.exception.BizException;
import com.kira.blog.mapper.RoleMapper;
import com.kira.blog.pojo.dto.RoleDTO;
import com.kira.blog.pojo.dto.ListQueryDTO;
import com.kira.blog.pojo.po.RolePO;
import com.kira.blog.response.common.Page;
import com.kira.blog.service.RoleService;
import com.kira.blog.utils.RoleValidateUtil;
import com.kira.blog.utils.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author datnv33
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private RoleMapper roleMapper;

    @Override
    public RolePO selectRoleByPrimaryKey(Long roleId, String roleRight) {
        logger.info("RoleServiceImpl, selectRoleByPrimaryKey - roleId is {}, roleRight-{}", roleId, roleRight);
        boolean withCheck = RoleValidateUtil.roleNeedCheck(roleRight);
        if (!withCheck) {
            throw new BizException(ExceptionEnum.USER_HAVE_NO_PERMISSION);
        }
        RolePO rolePO = roleMapper.selectByPrimaryKey(roleId);
        if (ObjectUtils.isEmpty(rolePO)) {
            throw new BizException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        return rolePO;
    }

    @Override
    public RolePO selectRoleByRoleRight(String roleRight) {
        logger.info("RoleServiceImpl, selectRoleByRoleRight - roleRight is {}", roleRight);
        return roleMapper.selectRoleByRoleRight(roleRight);
    }

    @Override
    public void createRole(String username, String roleRight, RoleDTO roleDTO) {
        logger.info("RoleServiceImpl, User-{}, createRole with roleRight-{}, roleDTO-{}", username, roleRight, roleDTO);
        RolePO rolePO = getRolePO(roleDTO, username, roleRight, true);
        roleMapper.insertSelective(rolePO);
        logger.info("RoleServiceImpl, User-{} createRole successfully!", username);
    }

    @Override
    public void updateRole(String username, String roleRight, RoleDTO roleDTO) {
        logger.info("RoleServiceImpl, User-{}, updateRole with roleRight-{}, roleDTO-{}", username, roleRight, roleDTO);
        RolePO rolePO = getRolePO(roleDTO, username, roleRight, false);
        roleMapper.updateByPrimaryKeySelective(rolePO);
        logger.info("RoleServiceImpl, User-{} updateRole successfully!", username);
    }

    private RolePO getRolePO(RoleDTO roleDTO, String username, String roleRight, boolean isCreate) {
        boolean withCheck = RoleValidateUtil.roleSADNeedCheck(roleRight);
        if (!withCheck) {
            throw new BizException(ExceptionEnum.USER_HAVE_NO_PERMISSION);
        }
        RolePO rolePO = roleMapper.selectRoleByRoleRight(roleDTO.getRoleRight());
        if (isCreate && !ObjectUtils.isEmpty(rolePO)) {
            throw new BizException(ExceptionEnum.ROLE_HAD_EXIST);
        }
        if (!isCreate && ObjectUtils.isEmpty(rolePO)) {
            throw new BizException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        rolePO = new RolePO();
        BeanUtils.copyProperties(roleDTO, rolePO);
        if (!StringUtils.isEmpty(username)) {
            rolePO.setUpdatedBy(username);
            rolePO.setCreatedBy(username);
        }
        return rolePO;
    }

    @Override
    public void deleteRoleById(Long roleId, String roleRight) {
        logger.info("RoleServiceImpl, deleteRoleById - roleId is {}, roleRight-{}", roleId, roleRight);
        boolean withCheck = RoleValidateUtil.roleSADNeedCheck(roleRight);
        if (!withCheck) {
            throw new BizException(ExceptionEnum.USER_HAVE_NO_PERMISSION);
        }
        RolePO rolePO = roleMapper.selectByPrimaryKey(roleId);
        if (ObjectUtils.isEmpty(rolePO)) {
            throw new BizException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        if (rolePO.getRoleRight().equals(RoleConst.ROLE_RIGHT_SAD) ||
                rolePO.getRoleRight().equals(RoleConst.ROLE_RIGHT_MC) ||
                rolePO.getRoleRight().equals(RoleConst.ROLE_RIGHT_USER) ||
                rolePO.getRoleRight().equals(RoleConst.ROLE_RIGHT_NA)) {
            throw new BizException(ExceptionEnum.ROLE_CANNOT_DELETE);
        }
        roleMapper.deleteByPrimaryKey(rolePO.getRoleId());
        logger.info("RoleServiceImpl, deleteRoleById role is-{} successfully!", rolePO.getRoleName());
    }

    @Override
    public Page<RolePO> getListRoles(String roleRight, String pageNo, String pageSize, String startTime, String endTime) {
        try {
            // 1. compare roleRight if need to do the maker-checker flow.
            boolean withCheck = RoleValidateUtil.roleNeedCheck(roleRight);
            if (!withCheck) {
                throw new BizException(ExceptionEnum.USER_HAVE_NO_PERMISSION);
            }
            Long startDay;
            Long endDay;
            if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
                startDay = TimeUtil.getTimestamp(false);
                endDay = TimeUtil.getTimestamp(true);
            } else {
                startDay = TimeUtil.month2Timestamp(startTime, "start");
                endDay = TimeUtil.month2Timestamp(endTime, "end");
            }
            int pn = Integer.parseInt(pageNo);
            int ps = Integer.parseInt(pageSize);
            ListQueryDTO listQueryDTO = new ListQueryDTO(pn, ps, (pn - 1) * ps, startDay, endDay);
            int total = roleMapper.countRoles(listQueryDTO);
            if (total == 0) {
                return new Page<>(GlobalConst.DEFAULT_PAGE_NO, GlobalConst.DEFAULT_PAGE_SIZE, 0, null);
            }
            List<RolePO> appList = roleMapper.getListRoles(listQueryDTO);
            logger.info("UserServiceImpl - Get list user successfully!");
            return new Page<>(listQueryDTO.getPageNo(), listQueryDTO.getPageSize(), total, appList);
        } catch (NumberFormatException ex) {
            logger.error("Get list application error, {}", ex.getMessage());
            throw new BizException(ExceptionEnum.USER_GET_LIST_WRONG_PARAM);
        }
    }
}
