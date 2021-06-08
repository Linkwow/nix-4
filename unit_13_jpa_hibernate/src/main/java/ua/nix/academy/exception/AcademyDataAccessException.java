package ua.nix.academy.exception;

public class AcademyDataAccessException extends AcademyDataException {

    public AcademyDataAccessException(){

    }

    public AcademyDataAccessException(String message, Throwable throwable){
        super(message, throwable);
    }
}
