package com.kira.blog.constant;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    SUCCESS("000000", "Success"),
    EXCEPTION_UNKNOWN("999999", "Unknown Error"),
    SQL_EXCEPTION("999998", "SQL Exception"),
    VALIDATE_ERROR("999997", "Parameter Validate Error"),

    /*
     * 40xxxxxx: ms-app com.kira.blog.exception
     * */
    UNKNOWN_FR_ERROR("400001", "Unknown com.kira.blog.exception in FR."),
    UNKNOWN_OCR_ERROR("400002", "Unknown com.kira.blog.exception in OCR."),

    /*
     * 60xxxxxx: ms-admin com.kira.blog.exception
     * */
    USER_NOT_EXIST("600000", "User not exist."),
    USER_IN_EDIT("600001", "User is in edit."),
    USER_WITH_NO_ROLE("600002", "User have no roles."),
    USER_IS_DELETE("600003", "User is delete."),
    USER_HAD_EXIST("600004", "User had existed. Pls try another username"),
    USER_WRONG_PASSWORD("600005", "User wrong password."),
    USER_PASSWORD_NOT_EQUAL_CONFIRM_PASSWORD("600006", "User new password and confirm password is not equal."),
    USER_HAD_EXIST_IS_DELETED("600022", "User had existed, Do you want to active it?"),

    ROLE_NOT_EXIST("600007", "Role not exist."),
    ROLE_IN_EDIT("600008", "Role is in edit."),

    TOKEN_GENERATED_ERROR("600009", "Fail to generate token."),
    TOKEN_EXPIRED("600010", "Access token expire. You have been logout!"),

    COPY_WRITING_NOT_EXIST("600011", "This copy writing not exist."),
    COPY_WRITING_IN_EDIT("600012", "This copy writing is in edit status."),

    ERROR_MESSAGE_NOT_EXIST("600013", "This error message not exist."),
    ERROR_MESSAGE_HAD_EXIST("600014", "This error had been exist."),

    EVENT_NOT_EXIST("600015", "This event not exist."),
    EVENT_HAD_DELETED("600016", "This event had deleted."),
    EVENT_CANNOT_UPDATE_BY_ONESELF("600017", "Sorry, you cannot update your own request."),

    NOTICE_NOT_EXIST("600018", "This notification not exist."),
    NOTICE_IN_EDIT("600019", "This notification is in edit, cannot update now."),
    USERNAME_FORMAT_ERROR("600020", "Sorry, Login name format should be "),
    DUPLICATE_OPERATION("600021", "Please do not execute duplicate operation."),

    /*
     * 80xxxxxx: gateway com.kira.blog.exception
     * */
    AES_KEY_DECRYPT_ERROR("800000", "Decrypt AES KEY Error"),
    REQUEST_DECRYPT_ERROR("800001", "Decrypt Request Body Error"),

    /*
     * 90xxxxxx: Blog common service exceptions
     */
    USER_HAD_LOGIN("900100", "your account had log in the other place, do you need focus log in this place?");

    private String code;
    private String msg;

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
