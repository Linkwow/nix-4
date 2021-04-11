package ua.nix.calendar.data;

import ua.nix.calendar.entity.Date;

import static ua.nix.calendar.util.UtilClass.*;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static DataBase instance;
    private Integer id = 1;
    public List<Date> listDate = new ArrayList<>();

    public void create(List<Long> parameters, Boolean leapYear) {
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

    public Date read(int id) {
        Date date = new Date();
        for (Date d : listDate) {
            if (d.getId() == id) {
                date = d;
            }
        }
        return date;
    }

    public Date differenceBetweenTwoDate(Date firstDate, Date secondDate) {
        Date date = new Date();
        long ms = 0;
        if (firstDate.getAllDateImMilliseconds() > secondDate.getAllDateImMilliseconds()) {
            ms = firstDate.getAllDateImMilliseconds() - secondDate.getAllDateImMilliseconds();
        } else {
            ms = secondDate.getAllDateImMilliseconds() - firstDate.getAllDateImMilliseconds();
        }
        long day = ms / DAY_TO_MS_VALUE;
        long leapYear = leapYearCount(firstDate.getYear(), secondDate.getYear());
        long years = (day - leapYear) / DAY_IN_YEAR;
        day = day - leapYear - years * DAY_IN_YEAR;
        long timeInMs = ms % DAY_TO_MS_VALUE;
        long month = dayToMonth(day);
        day = day - monthToDay(month);
        long hours = timeInMs / HOUR_TO_MS_VALUE;
        long minutes = (timeInMs - hours * HOUR_TO_MS_VALUE) / MINUTE_TO_MS_VALUE;
        long seconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE) / SECONDS_TO_MS_VALUE;
        long milliseconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE - seconds * MINUTE_TO_MS_VALUE);
        date.setDay(day);
        date.setMonth(month);
        date.setYear(years);
        date.setHour(hours);
        date.setMinutes(minutes);
        date.setSeconds(seconds);
        date.setMilliseconds(milliseconds);
        return date;
    }

    public Date addTwoDate(Date firstDate, Date secondDate) {
        Date date = new Date();
        long years = firstDate.getYear() + secondDate.getYear();
        long month = firstDate.getMonth() + secondDate.getMonth();
        if(month > 12){
            years++;
            month -= 12;
        }
        long day = firstDate.getDay() + secondDate.getDay();
        if(day > 28 && month == 2 && !isCurrentYearLeap(years)) {
            month++;
            day -= 28;
        } else if(day > 29 && month == 2 && isCurrentYearLeap(years)) {
            month++;
            day -= 29;
        } else if(day > 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
            month++;
            if (month > 12) {
                years++;
                month -= 12;
            }
            day -= 31;
        } else if(day > 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            month++;
            day -= 30;
        }
        long ms = 0;
        ms = firstDate.getAllDateImMilliseconds() + secondDate.getAllDateImMilliseconds();
        long timeInMs = ms % DAY_TO_MS_VALUE;
        long hours = timeInMs / HOUR_TO_MS_VALUE;
        long minutes = (timeInMs - hours * HOUR_TO_MS_VALUE) / MINUTE_TO_MS_VALUE;
        long seconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE) / SECONDS_TO_MS_VALUE;
        long milliseconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE - seconds * MINUTE_TO_MS_VALUE);
        date.setDay(day);
        date.setMonth(month);
        date.setYear(years);
        date.setHour(hours);
        date.setMinutes(minutes);
        date.setSeconds(seconds);
        date.setMilliseconds(milliseconds);
        return date;
    }

    public Date addTwoDate(Date firstDate, Date secondDate) {
        Date date = new Date();
        long years = firstDate.getYear() + secondDate.getYear();
        long month = firstDate.getMonth() + secondDate.getMonth();
        if(month > 12){
            years++;
            month -= 12;
        }
        long day = firstDate.getDay() + secondDate.getDay();
        if(day > 28 && month == 2 && !isCurrentYearLeap(years)) {
            month++;
            day -= 28;
        } else if(day > 29 && month == 2 && isCurrentYearLeap(years)) {
            month++;
            day -= 29;
        } else if(day > 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
            month++;
            if (month > 12) {
                years++;
                month -= 12;
            }
            day -= 31;
        } else if(day > 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            month++;
            day -= 30;
        }
        long ms = 0;
        ms = firstDate.getAllDateImMilliseconds() + secondDate.getAllDateImMilliseconds();
        long timeInMs = ms % DAY_TO_MS_VALUE;
        long hours = timeInMs / HOUR_TO_MS_VALUE;
        long minutes = (timeInMs - hours * HOUR_TO_MS_VALUE) / MINUTE_TO_MS_VALUE;
        long seconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE) / SECONDS_TO_MS_VALUE;
        long milliseconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE - seconds * MINUTE_TO_MS_VALUE);
        date.setDay(day);
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