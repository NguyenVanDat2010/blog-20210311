package com.kira.blog.pojo.vo;

import com.kira.blog.pojo.po.AccessRightPO;
import lombok.Data;

import java.util.List;

@Data
public class LoginVO {

    private String username;

    private String roleName;

    private String roleRight;

    private String roleStatus;

    private List<AccessRightPO> accessRights;

    private String accessToken;
}
