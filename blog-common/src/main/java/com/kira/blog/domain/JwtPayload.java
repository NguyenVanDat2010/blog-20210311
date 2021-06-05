package com.kira.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtPayload {

    private String userUuid;

//    private String deviceId;

    private String username;

     private Map<String, String> userRoles;

//    private List<String> roleRights;

//    private List<String> roleCodes;

}
