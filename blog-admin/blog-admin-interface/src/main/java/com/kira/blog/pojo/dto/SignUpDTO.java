package com.kira.blog.pojo.dto;

import com.kira.blog.annotation.validation.NotBlank;
import com.kira.blog.annotation.validation.ValidParamRange;
import com.kira.blog.annotation.validation.ValidRegex;
import lombok.Data;

import static com.kira.blog.constant.ApplicationConst.*;

@Data
public class SignUpDTO {
    @NotBlank(message = "Full name cannot empty")
    private String fullName;

    @NotBlank(message = "Email cannot empty")
    @ValidRegex(regex = REGEX_EMAIL, message = "Email validate error. Ex: example@gnail.com")
    private String email;

    @NotBlank(message = "Username cannot empty")
    @ValidRegex(regex = REGEX_USERNAME, message = "Username is 3-15 characters long, no _ or . at the beginning/inside/end. Ex: kira")
    private String username;

    @NotBlank(message = "Password cannot empty")
    @ValidRegex(regex = REGEX_PASSWORD, message = "Minimum eight characters, at least one letter, one number and one special character. Ex:123456a@.")
    private String password;

    @NotBlank(message = "Confirm Password is required")
    @ValidRegex(regex = REGEX_PASSWORD, message = "Minimum eight characters, at least one letter, one number and one special character. Ex:123456a@.")
    private String confirmPassword;

    @NotBlank(message = "Birth date cannot empty")
    @ValidRegex(regex = REGEX_FORMAT_REQUEST_BIRTH_DATE, message = "Birth date validate error. Ex: 2021-01-20")
    private String birthday;

    @ValidRegex(regex = REGEX_FORMAT_PHONE, message = "Phone number validate error. Ex: 0966523586")
    @NotBlank(message = "Phone number cannot empty")
    private String phoneNumber;

    @NotBlank(message = "Gender is required")
//    @ValidParamRange(paramRange = PARAM_GENDER, message = "Error gender, it should be in (male, female, other)")
    private String gender;

    private String avatar;
}
