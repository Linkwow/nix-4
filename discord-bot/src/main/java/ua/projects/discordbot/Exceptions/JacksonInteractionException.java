package ua.projects.discordbot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JacksonInteractionException extends Exception {

    public static ResponseStatusException convertException(Throwable throwable){
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage(), throwable);
    }
}
