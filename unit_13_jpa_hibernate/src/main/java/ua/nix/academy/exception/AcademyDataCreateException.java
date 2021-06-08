package ua.nix.academy.exception;

public class AcademyDataCreateException extends AcademyDataException{

        public AcademyDataCreateException(){

        }

        public AcademyDataCreateException(String message, Throwable throwable){
            super(message, throwable);
        }
}
