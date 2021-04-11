package ua.nix.calendar.data;

import ua.nix.calendar.entity.MyDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBase {

    private static DataBase instance;
    private Integer id = 1;
    private final List<MyDate> listMyDate = new ArrayList<>();

    public void create(List<Long> parameters) {
        MyDate myDate = new MyDate();
        myDate.setDay(parameters.get(0));
        myDate.setMonth(parameters.get(1));
        myDate.setYear(parameters.get(2));
        myDate.setHour(parameters.get(3));
        myDate.setMinutes(parameters.get(4));
        myDate.setSeconds(parameters.get(5));
        myDate.setMilliseconds(parameters.get(6));
        myDate.setAllDateImMilliseconds(parameters.get(7));
        myDate.setId(id++);
        listMyDate.add(myDate);
    }

    public MyDate read(int id) throws Exception {
        if(listMyDate.isEmpty()) {
            throw new Exception();
        }
        MyDate myDate = new MyDate();
        for (MyDate d : listMyDate) {
            if (d.getId() == id) {
                myDate = d;
            }
        }
        return myDate;
    }

    public void ascSort(){
        Collections.sort(DataBase.getInstance().listMyDate);
    }

    public void descSort(){
        Collections.sort(DataBase.getInstance().listMyDate, Collections.reverseOrder());
    }

    public List<MyDate> getAll(){
        return listMyDate;
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }
}