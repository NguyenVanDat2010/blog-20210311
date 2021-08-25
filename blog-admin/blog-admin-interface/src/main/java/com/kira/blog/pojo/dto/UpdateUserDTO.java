package com.kira.blog.pojo.dto;

import com.kira.blog.annotation.validation.ValidParamRange;
import com.kira.blog.annotation.validation.ValidRegex;
import lombok.Data;

import static com.kira.blog.constant.BlogConst.*;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserDTO {

    private String userUuid;

    private String fullName;

//    @ValidRegex(regex = REGEX_EMAIL, message = "Email validate error. Ex: example@gmail.com")
//    private String email;

    @ValidRegex(regex = REGEX_PASSWORD, message = "Minimum eight characters, at least one letter, one number and one special character. Ex:123456a@.")
    private String oldPassword;

    @ValidRegex(regex = REGEX_PASSWORD, message = "Minimum eight characters, at least one letter, one number and one special character. Ex:123456a@.")
    private String newPassword;

    @ValidRegex(regex = REGEX_PASSWORD, message = "Minimum eight characters, at least one letter, one number and one special character. Ex:123456a@.")
    private String confirmPassword;

    @ValidRegex(regex = REGEX_FORMAT_REQUEST_BIRTH_DATE, message = "Birth date validate error. Ex: 2021-01-20")
    private String birthday;

    @ValidRegex(regex = REGEX_FORMAT_PHONE, message = "Phone number validate error. Ex: 0966523586")
    private String phoneNumber;

    @ValidParamRange(paramRange = PARAM_GENDER, message = "Error gender, it should be in (male, female, other)")
    private String gender;

    @ValidRegex(regex = REGEX_BASE64_IMAGE, message = "Bas64 image should be start by \"data:image/{formatImage};base64,{base64String}\"")
    private String avatar;
}
