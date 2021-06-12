package ua.nix.finance.exceptions;

public class CSVFileException extends FinanceExceptions{

    public CSVFileException(String message) {
        super(message);
    }

    public CSVFileException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
