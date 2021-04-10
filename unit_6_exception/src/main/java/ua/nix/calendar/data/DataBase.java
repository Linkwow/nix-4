package ua.nix.calendar.data;

import ua.nix.calendar.entity.Date;
import static ua.nix.calendar.util.UtilClass.*;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static DataBase instance;
    private Integer id = 1;
    public List<Date> listDate = new ArrayList<>();

    public void create(List<Long> parameters, Boolean leapYear){
        Date date = new Date();
        date.setDay(parameters.get(0));
        date.setMonth(parameters.get(1));
        date.setYear(parameters.get(2));
        date.setHour(parameters.get(3));
        date.setMinutes(parameters.get(4));
        date.setSeconds(parameters.get(5));
        date.setMilliseconds(parameters.get(6));
        date.setDayCount(parameters.get(7));
        date.setAllDateImMilliseconds(parameters.get(8));
        date.setLeapYear(leapYear);
        date.setId(id++);
        listDate.add(date);
        System.out.println(date);
    }

    public Date read(int id){
        Date date = new Date();
        for(Date d : listDate){
            if(d.getId() == id){
                date = d;
            }
        }
        return date;
    }

    public Date subtractTwoDate(Date firstDate, Date secondDate){
        Date date = new Date();
        long ms = 0;
        if(firstDate.getAllDateImMilliseconds() > secondDate.getAllDateImMilliseconds()){
            ms = firstDate.getAllDateImMilliseconds() - secondDate.getAllDateImMilliseconds();
        } else {
            ms = secondDate.getAllDateImMilliseconds() - firstDate.getAllDateImMilliseconds();
        }
        long dateInDays = ms / DAY_TO_MS_VALUE;











        long leapYear = leapYearCount(firstDate.getYear(), secondDate.getYear());
        long timeInMs = ms % DAY_TO_MS_VALUE;

        long years = (dateInDays - leapYear) / DAY_IN_YEAR;
        dateInDays = dateInDays - years * DAY_IN_YEAR;
        long month = dayToMonth(dateInDays);
        if(isCurrentYearLeap(years + 1)){
            dateInDays = dateInDays - dayPassedSinceYearStart(month) - 1;
        } else {
            dateInDays = dateInDays - dayPassedSinceYearStart(month);
        }
        long hours = timeInMs / HOUR_TO_MS_VALUE;
        long minutes = (timeInMs - hours * HOUR_TO_MS_VALUE) / MINUTE_TO_MS_VALUE;
        long seconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE) / SECONDS_TO_MS_VALUE;
        long milliseconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE - seconds * MINUTE_TO_MS_VALUE);
        date.setDay(dateInDays);
        date.setMonth(month);
        date.setYear(years);
        date.setHour(hours);
        date.setMinutes(minutes);
        date.setSeconds(seconds);
        date.setMilliseconds(milliseconds);
        return date;
    }





    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }
}