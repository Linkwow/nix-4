package ua.nix.academy.exception;

public class AcademyDataException extends Exception {

    public AcademyDataException(){
        super();
    }

    public AcademyDataException(String message, Throwable throwable){
        super(message, throwable);
    }
}
