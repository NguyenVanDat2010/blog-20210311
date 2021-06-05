package com.kira.blog.exception;

import com.kira.blog.constant.ExceptionEnum;
import lombok.Getter;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = -4575692308128236204L;

    @Getter
    private String code;
    @Getter
    private String message;

    public BizException(ExceptionEnum ee) {
        this.code = ee.getCode();
        this.message = ee.getMsg();
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
