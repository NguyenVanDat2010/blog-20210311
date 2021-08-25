package com.kira.blog.api;

import com.kira.blog.pojo.dto.RoleDTO;
import com.kira.blog.response.ResponseBase;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("roles")
public interface RoleApi {

    @ApiOperation("Supper admin create role")
    @PostMapping("create-role")
    ResponseBase createRole(@RequestHeader("username") String username,
                            @RequestHeader("roleRight") String roleRight,
                            @RequestBody @Validated RoleDTO roleDTO);

}
