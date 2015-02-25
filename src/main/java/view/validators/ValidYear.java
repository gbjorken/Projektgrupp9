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
    
    /**
     * Felmeddelande som visas om året inte är inskrivet men på fel format
     * @return Okänt
     */
    String message() default "{invalidYear}";
    
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
     *Valideringsklass för year 
     */
    class YearValidator implements ConstraintValidator<ValidYear, String> 
    {
        /**
         * Okänt
         * @param constraintAnnotation 
         */
        @Override
        public void initialize(ValidYear constraintAnnotation) {
        }
        
        /**
         * Kontrollerar att det inskriva året är korrekt.
         * @param value Det inskrivna året
         * @param context Okänt
         * @return true om året är korrekt, annars false.
         */
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
        
        /**
         * Kontrollerar om ett antal år är inskrivet eller ej.
         * @param value Det inskrivna året
         * @param context Okänt
         * @return true om det inte skrivits in ett år, annars false
         */
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