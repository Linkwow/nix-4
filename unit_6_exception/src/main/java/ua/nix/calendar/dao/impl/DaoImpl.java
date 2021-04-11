package ua.nix.calendar.dao.impl;

import ua.nix.calendar.dao.Dao;
import ua.nix.calendar.data.DataBase;
import ua.nix.calendar.entity.MyDate;
import ua.nix.calendar.exceptions.DateException;

import java.util.List;

public class DaoImpl implements Dao {
    private static DaoImpl instance;

    @Override
    public void create(List<Long> parameters) {
        DataBase.getInstance().create(parameters);
    }

    @Override
    public MyDate read(int id) throws DateException {
        try {
            return DataBase.getInstance().read(id);
        } catch (Exception e){
            throw new DateException("Извините, попробуйте выбрать другую запись из базы данных");
        }
    }

    @Override
    public List<MyDate> getAll() {
        return DataBase.getInstance().getAll();
    }

    @Override
    public void ascSort() {
        DataBase.getInstance().ascSort();
    }

    @Override
    public void descSort() {
        DataBase.getInstance().descSort();
    }

    public static DaoImpl getInstance() {
        if(instance == null){
            instance = new DaoImpl();
        }
        return instance;
    }
}