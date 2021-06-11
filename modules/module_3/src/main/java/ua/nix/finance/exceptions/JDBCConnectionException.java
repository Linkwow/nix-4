package ua.nix.finance.exceptions;

public class JDBCConnectionException extends FinanceExceptions {

    public JDBCConnectionException(String message) {
        super(message);
    }

    public JDBCConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
