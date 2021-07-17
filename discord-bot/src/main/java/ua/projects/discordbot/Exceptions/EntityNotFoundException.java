package ua.projects.discordbot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends Exception {
//todo add a representation for all exception
    public static ResponseStatusException notFound(Integer id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id " + id + " not found.");
    }
}
