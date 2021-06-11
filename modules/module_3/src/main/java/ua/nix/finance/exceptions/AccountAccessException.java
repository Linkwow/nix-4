package ua.nix.finance.exceptions;

public class AccountAccessException extends FinanceExceptions {

    public AccountAccessException(String message) {
        super(message);
    }

    public AccountAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
