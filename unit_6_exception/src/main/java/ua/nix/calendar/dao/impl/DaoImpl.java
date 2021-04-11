package ua.nix.calendar.dao.impl;

import ua.nix.calendar.dao.Dao;
import ua.nix.calendar.data.DataBase;
import java.util.List;

public class DaoImpl implements Dao {
    private static DaoImpl instance;

    @Override
    public void create(List<Long> parameters, Boolean leapYear) {
        DataBase.getInstance().create(parameters, leapYear);
    }



    public static DaoImpl getInstance() {
        if(instance == null){
            instance = new DaoImpl();
        }
        return instance;
    }


}
