package view.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * The annotated target is checked to be a valid Year
 */
@Constraint(validatedBy = ValidYear.YearValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidYear {

    String message() default "{invalidYear}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class YearValidator implements ConstraintValidator<ValidYear, String> 
    {
        @Override
        public void initialize(ValidYear constraintAnnotation) {
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) 
        {
            if (isEmpty(value, context)) {
                return false;
            }            
            try {
                Double.parseDouble(value);
            } catch (NumberFormatException nfe) {
                return false;
            }
            
            return value.matches("[0-9][0-9]\\.[0-9][0-9]") || 
                    value.matches("[0-9]\\.[0-9][0-9]") ||
                    value.matches("[0-9][0-9]\\.[0-9]") ||
                    value.matches("[0-9]\\.[0-9]") ||
                    value.matches("[0-9]");
        }

        private boolean isEmpty(String value, ConstraintValidatorContext context) {
            if (value.length() == 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{noYear}").addConstraintViolation();
                return true;
            }
            return false;
        }
    }
}