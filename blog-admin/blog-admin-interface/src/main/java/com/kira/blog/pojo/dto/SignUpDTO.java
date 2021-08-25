package com.kira.blog.pojo.dto;

import com.kira.blog.annotation.validation.NotEmpty;
import com.kira.blog.annotation.validation.ValidParamRange;
import com.kira.blog.annotation.validation.ValidRegex;
import lombok.Data;

import static com.kira.blog.constant.BlogConst.*;

@Data
public class SignUpDTO {
    @NotEmpty(message = "Full name cannot empty")
    private String fullName;

    @ValidRegex(regex = REGEX_EMAIL, message = "Email validate error. Ex: example@gmail.com")
    @NotEmpty(message = "Email cannot empty")
    private String email;

    @NotEmpty(message = "Username cannot empty")
    @ValidRegex(regex = REGEX_USERNAME, message = "Username is 3-15 characters long, no _ or . at the beginning/inside/end. Ex: kira")
    private String username;

    @NotEmpty(message = "Password cannot empty")
    @ValidRegex(regex = REGEX_PASSWORD, message = "Minimum eight characters, at least one letter, one number and one special character. Ex:123456a@.")
    private String password;

    @NotEmpty(message = "Confirm Password is required")
    @ValidRegex(regex = REGEX_PASSWORD, message = "Minimum eight characters, at least one letter, one number and one special character. Ex:123456a@.")
    private String confirmPassword;

    @NotEmpty(message = "Birth date cannot empty")
    @ValidRegex(regex = REGEX_FORMAT_REQUEST_BIRTH_DATE, message = "Birth date validate error. Ex: 2021-01-20")
    private String birthday;

    @ValidRegex(regex = REGEX_FORMAT_PHONE, message = "Phone number validate error. Ex: 0966523586")
    @NotEmpty(message = "Phone number cannot empty")
    private String phoneNumber;

    @NotEmpty(message = "Gender is required")
    @ValidParamRange(paramRange = PARAM_GENDER, message = "Error gender, it should be in (Male, Female, Other)")
    private String gender;

    @ValidRegex(regex = REGEX_BASE64_IMAGE, message = "Bas64 image should be start by \"data:image/{formatImage};base64,{base64String}\"")
    private String avatar;
}
