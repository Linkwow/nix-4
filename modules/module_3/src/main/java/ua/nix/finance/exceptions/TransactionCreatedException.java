package ua.nix.finance.exceptions;

public class TransactionCreatedException extends FinanceExceptions {

    public TransactionCreatedException(String message) {
        super(message);
    }

    public TransactionCreatedException(String message, Throwable throwable){
        super(message, throwable);
    }
}
