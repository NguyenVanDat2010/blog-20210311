package com.kira.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtPayload {

//    private String deviceId;

    private String username;

    private String roleRight;

    private String roleStatus;

}
