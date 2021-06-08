package com.kira.blog.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class AccessRightPO {
    private String moduleCode;

    private Integer roleId;

    private String accessRight;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

}