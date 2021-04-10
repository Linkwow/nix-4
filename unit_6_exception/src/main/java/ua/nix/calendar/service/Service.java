package ua.nix.calendar.service;

import ua.nix.calendar.exceptions.impl.DateException;

public interface Service {
    void createDate(String input) throws DateException;
}
