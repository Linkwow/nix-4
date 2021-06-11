package ua.nix.finance.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckDigitValidator implements ConstraintValidator<NotValidDigit, Double> {

    private String notValidDigit;

    @Override
    public void initialize(NotValidDigit constraintAnnotation) {
        this.notValidDigit = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Double object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null) {
            return true;
        }
        return !object.equals(Double.parseDouble(notValidDigit));
    }
}
