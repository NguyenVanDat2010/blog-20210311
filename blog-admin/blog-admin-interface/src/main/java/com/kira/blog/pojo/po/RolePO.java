package com.kira.blog.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class RolePO {
    private Long roleId;

    private String roleName;

    private String roleRight;

    private String roleStatus;

    private String isEdit;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

}