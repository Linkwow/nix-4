package ua.projects.discordbot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends Exception {

    public static ResponseStatusException notFound(Integer id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Race with id " + id + " not found.");
    }
}
