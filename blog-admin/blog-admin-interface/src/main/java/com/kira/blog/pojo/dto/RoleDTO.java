package com.kira.blog.pojo.dto;

import com.kira.blog.annotation.validation.NotEmpty;
import com.kira.blog.annotation.validation.ValidRegex;
import lombok.Data;

import java.util.Date;

import static com.kira.blog.constant.BlogConst.*;

@Data
public class RoleDTO {

    @NotEmpty(message = "Role name cannot empty")
    private String roleName;

    @NotEmpty(message = "Role right cannot empty")
    @ValidRegex(regex = REGEX_ROLE, message = "Error roleRight, it should be start by ROLE_ and have no lowercase, space and special characters (Ex: ROLE_USER)")
    private String roleRight;

    //@NotEmpty(message = "Role status cannot empty")
    private String roleStatus;

}
