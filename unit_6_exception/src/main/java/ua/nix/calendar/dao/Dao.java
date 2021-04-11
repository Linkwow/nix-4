package ua.nix.calendar.dao;

import ua.nix.calendar.entity.MyDate;
import ua.nix.calendar.exceptions.DateException;

import java.util.List;

public interface Dao {

    void create(List<Long> parameters);
    MyDate read(int id) throws DateException;
    List<MyDate> getAll();
    void ascSort();
    void descSort();
}
