package ua.projects.discordbot.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RaceNotFoundException extends Exception {

    public static ResponseStatusException notFound(Integer id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Race with id " + id + " not found.");
    }
}
