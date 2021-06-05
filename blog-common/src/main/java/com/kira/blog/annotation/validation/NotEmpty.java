package com.kira.blog.annotation.validation;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.exception.InvalidInputException;
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
@Constraint(validatedBy = NotEmpty.NotEmptyValidator.class)
public @interface NotEmpty {

    String message() default "invalid input";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {
        private String message;

        @Override
        public void initialize(NotEmpty constraintAnnotation) {
            this.message = constraintAnnotation.message();
        }

        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
            if (!ObjectUtils.isEmpty(obj)) {
                return true;
            }
            throw new InvalidInputException(ExceptionEnum.VALIDATE_ERROR.getCode(), message);
        }
    }
}
