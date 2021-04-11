package ua.nix.calendar.dao;

import ua.nix.calendar.entity.MyDate;

import java.util.List;

public interface Dao {

    void create(List<Long> parameters, Boolean leapYear);
    MyDate read(int id);

}
