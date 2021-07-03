package com.kira.blog.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class UserPO {
    private String userUuid;

    private Long roleId;

    private String fullName;

    private String email;

    private String username;

    private String password;

    private String cfPassword;

    private String oldPassword;

    private String birthday;

    private String phoneNumber;

    private String gender;

    private String userStatus;

    private String isEdit;

    private String isDelete;

    private Date lastLoginTime;

    private String whenCreated;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    private String avatar;

}