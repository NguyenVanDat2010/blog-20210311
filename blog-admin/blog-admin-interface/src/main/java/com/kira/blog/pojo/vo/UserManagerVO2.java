package com.kira.blog.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserManagerVO2 {

    private String userUuid;

    private Long roleId;

    private String fullName;

    private String email;

    private String username;

    private String birthday;

    private String phoneNumber;

    private String gender;

    private String userStatus;

    private String isEdit;

    private String isDelete;

    private String roleName;

    private String roleRight;

    private String roleStatus;

    private Date lastLoginTime;

    private String whenCreated;

    private Date createdDate;

    private Date updatedDate;
}
