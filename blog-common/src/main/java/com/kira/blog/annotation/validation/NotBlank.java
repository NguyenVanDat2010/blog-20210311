package com.kira.blog.annotation.validation;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.exception.InvalidInputException;
import org.apache.commons.lang3.StringUtils;

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
@Constraint(validatedBy = NotBlank.NotBlankValidator.class)
public @interface NotBlank {

    String message() default "invalid input";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class NotBlankValidator implements ConstraintValidator<NotBlank, Object> {
        private String message;

        @Override
        public void initialize(NotBlank constraintAnnotation) {
            this.message = constraintAnnotation.message();
        }

        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext context) {
            if (null != obj && !StringUtils.isBlank(obj.toString())) {
                return true;
            }
            throw new InvalidInputException(ExceptionEnum.VALIDATE_ERROR.getCode(), message);
        }
    }
}
