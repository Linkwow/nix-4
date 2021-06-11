package ua.nix.finance.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckDigitValidator.class)
public @interface NotValidDigit {

    String message() default "{Validate amount" + "message()}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value();
}