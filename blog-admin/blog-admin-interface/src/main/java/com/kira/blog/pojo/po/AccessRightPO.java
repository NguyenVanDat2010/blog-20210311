package com.kira.blog.pojo.po;

import com.kira.blog.annotation.validation.ValidParamRange;
import com.kira.blog.constant.RoleConst;
import lombok.Data;

import java.util.Date;

@Data
public class AccessRightPO {
    private String moduleCode;

//    private Integer roleId;

    @ValidParamRange(
            paramRange = RoleConst.ACCESS_RIGHT,
            message = "Error access right code, it should be in (RO, NA, RW)"
    )
    private String accessRight;

    private String sort;

}