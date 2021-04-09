package ua.nix.calendar.data;

import ua.nix.calendar.entity.Date;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static DataBase instance;
    private Integer id = 1;

    private List<Date> listDate = new ArrayList<>();

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void create(long[] values){
        Date date = new Date();
        date.setDay(values[0] + 1);
        date.setMonth(values[1] + 1);
        date.setYear(values[2] + 1);
        date.setHour(values[3]);
        date.setMinutes(values[4]);
        date.setSeconds(values[5]);
        date.setMilliseconds(values[6]);
        date.setMs(values[7]);
        date.setId(id++);
        System.out.println(date);
    }

}
