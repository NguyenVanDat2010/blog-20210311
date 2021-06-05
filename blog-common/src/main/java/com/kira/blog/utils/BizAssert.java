package com.kira.blog.utils;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.exception.BizException;
import com.kira.blog.exception.InvalidInputException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

public abstract class BizAssert {
    public static void isTrue(boolean expression, ExceptionEnum message) {
        if (!(expression)) {
            throw new BizException(message.getCode(), message.getMsg());
        }
    }

    public static void isNull(Object object, ExceptionEnum exceptionEnum) {
        if (object != null) {
            throw new BizException(exceptionEnum);
        }
    }

    public static void notNullOrEmpty(String text, ExceptionEnum exceptionEnum) {
        if (null == text || text.trim().length() == 0) {
            throw new BizException(exceptionEnum);
        }
    }

    public static void notNullOrEmpty(String text, String invalidInputErrorMessage) {
        if (null == text || text.trim().length() == 0) {
            throw new InvalidInputException(ExceptionEnum.VALIDATE_ERROR.getCode(), invalidInputErrorMessage);
        }
    }

    public static void notNullOrEmpty(Object obj, String invalidInputErrorMessage) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new InvalidInputException(ExceptionEnum.VALIDATE_ERROR.getCode(), invalidInputErrorMessage);
        }
    }

    public static void notNull(Object object, ExceptionEnum exceptionEnum) {
        if (object == null) {
            throw new BizException(exceptionEnum);
        }
    }

    public static void hasLength(String text, ExceptionEnum exceptionEnum) {
        if (!(StringUtils.hasLength(text))) {
            throw new BizException(exceptionEnum);
        }
    }

    public static void hasText(String text, ExceptionEnum exceptionEnum) {
        if (!(StringUtils.hasText(text))) {
            throw new BizException(exceptionEnum);
        }
    }

    public static void notEmpty(Object[] array, ExceptionEnum exceptionEnum) {
        if (ObjectUtils.isEmpty(array)) {
            throw new BizException(exceptionEnum);
        }
    }

    public static void noNullElements(Object[] array, ExceptionEnum exceptionEnum) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new BizException(exceptionEnum);
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static void notEmpty(Collection collection, ExceptionEnum exceptionEnum) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(exceptionEnum);
        }
    }

    public static void notEmpty(Collection collection, String invalidInputErrorMessage) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new InvalidInputException(ExceptionEnum.VALIDATE_ERROR.getCode(), invalidInputErrorMessage);
        }
    }

    @SuppressWarnings("rawtypes")
    public static void notEmpty(Map map, ExceptionEnum exceptionEnum) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(exceptionEnum);
        }
    }

    public static void state(boolean expression, ExceptionEnum exceptionEnum) {
        if (!(expression)) {
            throw new BizException(exceptionEnum);
        }
    }

}