package ua.nix.calendar.service;

import ua.nix.calendar.exceptions.impl.DateExceptions;

public interface Service {
    void createDate(String input) throws DateExceptions;
}
