package ua.nix.finance.exceptions;

public class FinanceExceptions extends Exception {

    public FinanceExceptions(String message){
        super(message);
    }

    public FinanceExceptions(String message, Throwable throwable){
        super(message, throwable);
    }
}
