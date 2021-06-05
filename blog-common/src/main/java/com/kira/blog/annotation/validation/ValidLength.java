package com.kira.blog.annotation.validation;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author nvdat2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = ValidLength.ValidLengthValidator.class)
public @interface ValidLength {

    String message() default "invalid input length";

    int max();

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ValidLengthValidator implements ConstraintValidator<ValidLength, Object> {
        private String message;
        private int max;
        private int min;
        private static final Logger logger = LoggerFactory.getLogger(ValidLengthValidator.class);

        @Override
        public void initialize(ValidLength constraintAnnotation) {
            this.message = constraintAnnotation.message();
            this.min = constraintAnnotation.min();
            this.max = constraintAnnotation.max();
        }

        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
            logger.info("ValidLengthValidator, value is: {}", obj.toString());
            if (!ObjectUtils.isEmpty(obj)) {
                if (obj.toString().length() >= this.min && obj.toString().length() <= this.max) {
                    return true;
                }
            } else {
                return true;
            }
            throw new InvalidInputException(ExceptionEnum.VALIDATE_ERROR.getCode(), message);
        }
    }
}
