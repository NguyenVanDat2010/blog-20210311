package com.kira.blog.annotation.validation;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.exception.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nvdat2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = ValidRegex.ValidRegexValidator.class)
public @interface ValidRegex {

    String message() default "invalid input regex";

    String regex() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ValidRegexValidator implements ConstraintValidator<ValidRegex, Object> {
        private String regex;
        private String message;
        private static final Logger logger = LoggerFactory.getLogger(ValidRegexValidator.class);

        @Override
        public void initialize(ValidRegex constraintAnnotation) {
            this.regex = constraintAnnotation.regex();
            this.message = constraintAnnotation.message();
        }

        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
            logger.info("ValidRegexValidator, value is: {}", obj.toString());
            if (obj == null || StringUtils.isBlank(obj.toString()) || StringUtils.isBlank(regex)) {
                return true;
            }

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(obj.toString());
            if (!matcher.matches()) {
                throw new InvalidInputException(ExceptionEnum.VALIDATE_ERROR.getCode(), message);
            }

            return true;
        }
    }
}
