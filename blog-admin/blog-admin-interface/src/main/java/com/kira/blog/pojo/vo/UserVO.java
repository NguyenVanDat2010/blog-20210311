package com.kira.blog.pojo.vo;

import com.kira.blog.pojo.po.AccessRightPO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserVO {
    private String userUuid;

    private Long roleId;

    private String fullName;

    private String email;

    private String username;

    private String password;

    private String birthday;

    private String phoneNumber;

    private String gender;

    private String userStatus;

    private String isEdit;

    private String isDelete;

    private String roleName;

    private String roleRight;

    private String roleStatus;

    private List<AccessRightPO> accessRights;

    private Date lastLoginTime;

    private String whenCreated;

    private Date createdDate;

    private Date updatedDate;
}
