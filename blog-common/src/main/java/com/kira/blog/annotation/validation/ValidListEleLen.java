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
import java.util.List;

/**
 * @author nvdat2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = ValidListEleLen.ValidListEleLenValidator.class)
public @interface ValidListEleLen {

    String message() default "invalid param range input";

    int max();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ValidListEleLenValidator implements ConstraintValidator<ValidListEleLen, Object> {
        private String message;
        private int max;
        private static final Logger logger = LoggerFactory.getLogger(ValidListEleLenValidator.class);

        @Override
        public void initialize(ValidListEleLen constraintAnnotation) {
            this.message = constraintAnnotation.message();
            this.max = constraintAnnotation.max();
        }

        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
            if (!ObjectUtils.isEmpty(obj)) {
                List<String> list = (List<String>) obj;
                for (String s : list) {
                    if (s.length() > max) {
                        throw new InvalidInputException(ExceptionEnum.VALIDATE_ERROR.getCode(), message);
                    }
                }
            }
            return true;
        }
    }
}
