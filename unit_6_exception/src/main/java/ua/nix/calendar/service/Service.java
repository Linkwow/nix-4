package ua.nix.calendar.service;

import ua.nix.calendar.exceptions.DateException;

public interface Service {
    void createDate(String input) throws DateException;
}
