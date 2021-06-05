package com.kira.blog.annotation.validation;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

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
@Constraint(validatedBy = ValidParamRange.ValidParamRangeValidator.class)
public @interface ValidParamRange {

    String message() default "invalid param range input";

    String paramRange();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ValidParamRangeValidator implements ConstraintValidator<ValidParamRange, Object> {
        private String message;
        private String paramRange;
        private static final Logger logger = LoggerFactory.getLogger(ValidParamRangeValidator.class);

        @Override
        public void initialize(ValidParamRange constraintAnnotation) {
            this.message = constraintAnnotation.message();
            this.paramRange = constraintAnnotation.paramRange();
        }

        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
            logger.info("ValidParamRangeValidator, value is: {}", obj.toString());
            if (!StringUtils.isEmpty(paramRange)) {
                String[] split = paramRange.split(",");
                for (String str : split) {
                    if (str.equalsIgnoreCase(obj.toString())) {
                        return true;
                    }
                }
            }
            throw new InvalidInputException(ExceptionEnum.VALIDATE_ERROR.getCode(), message);
        }
    }

}
