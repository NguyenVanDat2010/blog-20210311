package com.kira.blog.exception;

import com.kira.blog.constant.ExceptionEnum;
import lombok.Getter;

public class InvalidInputException extends RuntimeException {

    @Getter
    private String code;
    @Getter
    private String message;

    public InvalidInputException(ExceptionEnum ee) {
        this.code = ee.getCode();
        this.message = ee.getMsg();
    }

    public InvalidInputException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
