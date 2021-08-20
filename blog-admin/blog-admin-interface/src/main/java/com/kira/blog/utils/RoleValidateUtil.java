package com.kira.blog.utils;


import com.kira.blog.constant.RoleConst;
import com.kira.blog.exception.BizException;

public class RoleValidateUtil {

    public static boolean roleNeedCheck(String roleRight) {
        boolean withCheck;
        if (RoleConst.ROLE_RIGHT_NA.equals(roleRight)) {
            withCheck = false;
        } else if (RoleConst.ROLE_RIGHT_MC.equals(roleRight)) {
            withCheck = true;
        } else {
            throw new BizException("988888", "Bad role right");
        }
        return withCheck;
    }

}
