package com.kira.blog.response;

import com.kira.blog.constant.ExceptionEnum;

/**
 * com.kira.blog.response utils
 * @author Kira
 */
public class ResponseUtils {

    /**
     * success with no data
     */
    public static ResponseBase<?> ok() {
        return new ResponseBase<>(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMsg(), null);
    }

    /**
     * success with data
     */
    public static <T> ResponseBase<T> ok(T data) {
        return new ResponseBase<T>(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMsg(), data);
    }

    /**
     * fail
     */
    public static <T> ResponseBase<T> fail(ExceptionEnum ex) {
        return new ResponseBase<T>(ex.getCode(), ex.getMsg(), null);
    }

    /**
     * custom fail
     */
    public static <T> ResponseBase<T> fail(String code, String msg) {
        return new ResponseBase<T>(code, msg, null);
    }

    /**
     * error
     */
    public static <T> ResponseBase<?> error(ExceptionEnum ex) {
        return new ResponseBase<>(ex.getCode(), ex.getMsg(), null);
    }

    /**
     * custom com.kira.blog.response
     */
    public static <T> ResponseBase<T> result(String code, String msg, T data) {
        return new ResponseBase<T>(code, msg, data);
    }

}
