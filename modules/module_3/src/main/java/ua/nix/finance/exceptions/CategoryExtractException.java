package ua.nix.finance.exceptions;

public class CategoryExtractException extends FinanceExceptions{

    public CategoryExtractException(String message) {
        super(message);
    }

    public CategoryExtractException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
