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
 * Klassen checkar om E-post adressen som skrivits in innehåller korrekta 
 * tecken.
 */
@Constraint(validatedBy = ValidEmail.EmailValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    
    /**
     * Felmeddelande som visas om eposten är inskriven men på fel format.
     * @return Okänt
     */
    String message() default "{invalidEmail}";
    
    /**
     * Okänt
     * @return 
     */
    Class<?>[] groups() default {};
    
    /**
     * Okänt
     * @return 
     */
    Class<? extends Payload>[] payload() default {};
    
    /**
     *Valideringsklass för email 
     */
    class EmailValidator implements ConstraintValidator<ValidEmail, String> 
    {
        /**
         * Okänt.
         * @param constraintAnnotation 
         */
        @Override
        public void initialize(ValidEmail constraintAnnotation) {
        }
        
        /**
         * Kontrollerar att den inskrivna email-adressen är korrekt.
         * @param value Den inskrivna email-adressen
         * @param context Okänt
         * @return true om email-adressen är korrekt, annars false.
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) 
        {
            if(isEmpty(value, context)) {
                return false;
            }
            
            return value.matches("([a-z0-9]+?)@([a-z0-9]+?)\\.([a-z0-9]+?)") || 
                   value.matches("([a-z0-9]+?)\\.([a-z0-9]+?)@([a-z0-9]+?)\\.([a-z0-9]+?)");
        }
        
        /**
         * Kontrollerar om en E-post skrivits in eller ej.
         * @param value Den inskrivna email-adressen
         * @param context Okänt
         * @return true om e-posten saknas annars false.
         */
        private boolean isEmpty(String value, ConstraintValidatorContext context) {
            if (value.length() == 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{noEmail}").addConstraintViolation();
                return true;
            }
            return false;
        }
    }
}
