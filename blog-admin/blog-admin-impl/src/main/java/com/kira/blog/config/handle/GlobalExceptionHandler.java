package com.kira.blog.config.handle;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.exception.BizException;
import com.kira.blog.exception.InvalidInputException;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * catch BizException: business exception
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseBase<?> exceptionHandler(BizException e) {
        logger.error("Business exception message is: {}", e.getMessage());
        return ResponseUtils.fail(e.getCode(), e.getMessage());
    }

    /**
     * catch InvalidInputException: invalid input exception
     */
    @ExceptionHandler(value = {InvalidInputException.class, ValidationException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBase<?> exceptionHandler(InvalidInputException e) {
        logger.error("Invalid message is: {}", e.getMessage());
        return ResponseUtils.fail(e.getCode(), e.getMessage());
    }


    /**
     * catch SQLException: sql exception
     * */
    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBase<?> exceptionHandler(SQLException e) {
        logger.error("SQLException message is: {}", e.getMessage());
        return ResponseUtils.fail(ExceptionEnum.SQL_EXCEPTION.getCode(), e.toString());
    }

    /**
     * catch Exception: unknown exception
     * */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBase<?> exceptionHandler(Exception e) {
        logger.error("Unknown exception message is: {}",e.getMessage(), e);
        return ResponseUtils.fail(ExceptionEnum.EXCEPTION_UNKNOWN.getCode(), e.toString());
    }

}
