package com.kira.blog.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    @NotBlank(message = "Username can not be empty")
    private String username;

    @NotBlank(message = "Password can not be empty")
    private String password;

    private String forceLogin;

    private String remoteAddr;
}
