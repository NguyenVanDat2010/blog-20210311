package com.kira.blog.constant;

public interface RoleConst {

    String ROLE_STATUS_ACTIVE = "1";

    String ROLE_STATUS_DELETE = "0";

    String ACCESS_RIGHT = "RO,RW";

    String ROLE_RIGHT = "ROLE_SAD,ROLE_MC,ROLE_USER";

    // need maker and checker workflow
    String ROLE_RIGHT_USER = "ROLE_USER";
    String ROLE_RIGHT_SAD = "ROLE_SAD";
    String ROLE_RIGHT_MC = "ROLE_MC";

    // no need maker and checker workflow
    String ROLE_RIGHT_NA = "ROLE_NA";

    Integer ROLE_SIZE = 0;

}