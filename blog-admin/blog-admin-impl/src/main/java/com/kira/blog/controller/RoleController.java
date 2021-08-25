package com.kira.blog.controller;

import com.kira.blog.api.RoleApi;
import com.kira.blog.pojo.dto.RoleDTO;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.ResponseUtils;
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
}
