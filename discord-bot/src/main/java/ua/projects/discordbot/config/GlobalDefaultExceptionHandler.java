package ua.projects.discordbot.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ua.projects.discordbot.exceptions.ValidationException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
class GlobalDefaultExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ValidationException.class)
    public ModelAndView errorHandle(HttpServletRequest req, Exception e) {
        ModelAndView errorView = new ModelAndView();
        errorView.addObject("exception", e.getMessage());
        errorView.addObject("url", req.getRequestURL());
        errorView.addObject("status", "Bad Request");
        errorView.addObject("status-code", 400);
        errorView.setViewName("error");
        return errorView;
    }
}
