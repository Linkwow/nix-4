package ua.nix.calendar.dao;

import java.util.List;

public interface Dao {

    void create(List<Long> parameters, Boolean leapYear);

}
