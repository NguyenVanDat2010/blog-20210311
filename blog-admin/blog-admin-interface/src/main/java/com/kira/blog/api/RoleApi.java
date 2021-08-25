package com.kira.blog.api;

import com.kira.blog.pojo.dto.RoleDTO;
import com.kira.blog.response.ResponseBase;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("roles")
public interface RoleApi {

    ResponseBase createRole(@RequestHeader("roleRight") String roleRight, @RequestBody @Validated RoleDTO roleDTO);
}
