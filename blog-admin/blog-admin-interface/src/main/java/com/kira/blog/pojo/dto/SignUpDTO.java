package com.kira.blog.pojo.dto;

import com.kira.blog.annotation.validation.NotEmpty;
import com.kira.blog.annotation.validation.NotEmpty;
import com.kira.blog.annotation.validation.ValidParamRange;
import com.kira.blog.annotation.validation.ValidRegex;
import lombok.Data;

import javax.validation.Valid;

import static com.kira.blog.constant.ApplicationConst.*;

@Data
//@Valid
public class SignUpDTO {
    @javax.validation.constraints.NotEmpty(message = "Full name cannot empty")
    @NotEmpty(message = "Full name cannot empty1")
    private String fullName;

    @javax.validation.constraints.NotEmpty(message = "Email cannot empty")
    @ValidRegex(regex = REGEX_EMAIL, message = "Email validate error. Ex: example@gmail.com")
    @NotEmpty(message = "Email cannot empty1")
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
    @ValidParamRange(paramRange = PARAM_GENDER, message = "Error gender, it should be in (male, female, other)")
    private String gender;

    private String avatar;
}
