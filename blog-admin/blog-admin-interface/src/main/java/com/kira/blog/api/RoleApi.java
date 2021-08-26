package com.kira.blog.api;

import com.kira.blog.pojo.dto.RoleDTO;
import com.kira.blog.pojo.po.RolePO;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.common.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("roles")
public interface RoleApi {

    @ApiOperation("Supper admin create role")
    @PostMapping("create-role")
    ResponseBase createRole(@RequestHeader("username") String username,
                            @RequestHeader("roleRight") String roleRight,
                            @RequestBody @Validated RoleDTO roleDTO);

    @ApiOperation("Supper admin update role")
    @PutMapping("update-role")
    ResponseBase updateRole(@RequestHeader("username") String username,
                            @RequestHeader("roleRight") String roleRight,
                            @RequestBody @Validated RoleDTO roleDTO);


    @ApiOperation("Get role by Id")
    @GetMapping("{roleId}")
    ResponseBase<RolePO> getRoleById(@PathVariable("roleId") Long roleId,
                                     @RequestHeader("roleRight") String roleRight);

    @ApiOperation("Delete role by Id")
    @DeleteMapping("{roleId}")
    ResponseBase deleteRoleById(@PathVariable("roleId") Long roleId,
                                @RequestHeader("roleRight") String roleRight);

    @ApiOperation("Get list roles with roleRight")
    @GetMapping()
    ResponseBase<Page<RolePO>> getListRoles(@RequestHeader("roleRight") String roleRight,
                                            @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
                                            @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
                                            @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime);
}
