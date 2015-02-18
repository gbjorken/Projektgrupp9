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
 * Klassen checkar om personnumret som skrivits in innehåller korrekta tecken.
 * The annotated target is checked to be a valid SSN
 */
@Constraint(validatedBy = ValidSSN.SSNValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSSN {

    String message() default "{invalidSSN}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SSNValidator implements ConstraintValidator<ValidSSN, String> 
    {
        /**
         * Okänt.
         * @param constraintAnnotation 
         */
        @Override
        public void initialize(ValidSSN constraintAnnotation) {
        }

        /**
        * Är av typen boolean. Om allt gått korrekt returneras True.
        * Kontrollerar om tecknena i det inskrivna personnumret är av
        * godkända tecken.
        * @param value Okänt
        * @param context Okänt
        * @return TrueOrFalse
        */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) 
        {
            if (isEmpty(value, context)) {
                return false;
            }
          
            return value.matches("([0-9]{6})-([0-9]{4})") || 
                    value.matches("([0-9]{8})-([0-9]{4})") ||
                    value.matches("([0-9]{3})-([0-9]{2})-([0-9]{2})");
        }

        /**
        * Är av typen boolean. Returnerar True om _inget_ personnummer 
        * skrivits in. Kontrollerar om en E-post skrivits in eller ej.
        * @param value Okänt
        * @param context Okänt
        * @return TrueOrFalse
        */
        private boolean isEmpty(String value, ConstraintValidatorContext context) {
            if (value.length() == 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{noSSN}").addConstraintViolation();
                return true;
            }
            return false;
        }
    }
}