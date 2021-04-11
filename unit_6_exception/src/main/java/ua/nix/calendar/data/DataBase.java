package ua.nix.calendar.data;

import ua.nix.calendar.entity.MyDate;
import ua.nix.calendar.exceptions.impl.DateException;

import static ua.nix.calendar.util.UtilClass.*;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static DataBase instance;
    private Integer id = 1;
    public List<MyDate> listMyDate = new ArrayList<>();

    public void create(List<Long> parameters, Boolean leapYear) {
        MyDate myDate = new MyDate();
        myDate.setDay(parameters.get(0));
        myDate.setMonth(parameters.get(1));
        myDate.setYear(parameters.get(2));
        myDate.setHour(parameters.get(3));
        myDate.setMinutes(parameters.get(4));
        myDate.setSeconds(parameters.get(5));
        myDate.setMilliseconds(parameters.get(6));
        myDate.setDayCount(parameters.get(7));
        myDate.setAllDateImMilliseconds(parameters.get(8));
        myDate.setLeapYear(leapYear);
        myDate.setId(id++);
        listMyDate.add(myDate);
        System.out.println(myDate);
    }

    public MyDate read(int id) {
        MyDate myDate = new MyDate();
        for (MyDate d : listMyDate) {
            if (d.getId() == id) {
                myDate = d;
            }
        }
        return myDate;
    }

    public MyDate differenceBetweenTwoDate(MyDate firstMyDate, MyDate secondMyDate) {
        MyDate myDate = new MyDate();
        long ms = 0;
        if (firstMyDate.getAllDateImMilliseconds() > secondMyDate.getAllDateImMilliseconds()) {
            ms = firstMyDate.getAllDateImMilliseconds() - secondMyDate.getAllDateImMilliseconds();
        } else {
            ms = secondMyDate.getAllDateImMilliseconds() - firstMyDate.getAllDateImMilliseconds();
        }
        long day = ms / DAY_TO_MS_VALUE;
        long leapYear = leapYearCount(firstMyDate.getYear(), secondMyDate.getYear());
        long years = (day - leapYear) / DAY_IN_YEAR;
        day = day - leapYear - years * DAY_IN_YEAR;
        long timeInMs = ms % DAY_TO_MS_VALUE;
        long month = dayToMonth(day);
        day = day - monthToDay(month);
        long hours = timeInMs / HOUR_TO_MS_VALUE;
        long minutes = (timeInMs - hours * HOUR_TO_MS_VALUE) / MINUTE_TO_MS_VALUE;
        long seconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE) / SECONDS_TO_MS_VALUE;
        long milliseconds = (timeInMs - hours * HOUR_TO_MS_VALUE - minutes * MINUTE_TO_MS_VALUE - seconds * MINUTE_TO_MS_VALUE);
        myDate.setDay(day);
        myDate.setMonth(month);
        myDate.setYear(years);
        myDate.setHour(hours);
        myDate.setMinutes(minutes);
        myDate.setSeconds(seconds);
        myDate.setMilliseconds(milliseconds);
        return myDate;
    }

    public MyDate addTwoDate(MyDate firstMyDate, MyDate secondMyDate) throws DateException {
        MyDate myDate = new MyDate();
        long years = firstMyDate.getYear() + secondMyDate.getYear();
        long month = firstMyDate.getMonth() + secondMyDate.getMonth();
        long day = firstMyDate.getDay() + secondMyDate.getDay();
        long hours = firstMyDate.getHour() + secondMyDate.getHour();
        long minutes = firstMyDate.getMinutes() + secondMyDate.getMinutes();
        long seconds = firstMyDate.getSeconds() + secondMyDate.getSeconds();
        long milliseconds = firstMyDate.getMilliseconds() + secondMyDate.getMilliseconds();
        if (milliseconds > 999) {
            seconds++;
            milliseconds -= 1000;
        }
        if (seconds > 59) {
            minutes++;
            seconds -= 60;
        }
        if (minutes > 59) {
            hours++;
            minutes -= 60;
        }
        if (hours > 23) {
            day++;
            hours -= 24;
        }
        if (day > 28 && month == 2 && !isCurrentYearLeap(years)) {
            month++;
            day -= 28;
        }
        if (day > 29 && month == 2 && isCurrentYearLeap(years)) {
            month++;
            day -= 29;
        }
        if (day > 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            month++;
            day -= 30;
        }
        if (day > 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
            month++;
            day -= 31;
        }
        if (month > 12) {
            years++;
            month -= 12;
        }
        if (years > 9999) {
            throw new DateException("Извините, попробуйте повторно ввести год меньше, так как сумма превышает 4 - ех значное число");
        }

        myDate.setDay(day);
        myDate.setMonth(month);
        myDate.setYear(years);
        myDate.setHour(hours);
        myDate.setMinutes(minutes);
        myDate.setSeconds(seconds);
        myDate.setMilliseconds(milliseconds);
        return myDate;
    }

    public MyDate subtractTwoDate(MyDate firstMyDate, MyDate secondMyDate) throws DateException {
        MyDate myDate = new MyDate();
        long years = firstMyDate.getYear() - secondMyDate.getYear();
        long month = firstMyDate.getMonth() - secondMyDate.getMonth();
        long day = firstMyDate.getDay() - secondMyDate.getDay();
        long hours = firstMyDate.getHour() - secondMyDate.getHour();
        long minutes = firstMyDate.getMinutes() - secondMyDate.getMinutes();
        long seconds = firstMyDate.getSeconds() - secondMyDate.getSeconds();
        long milliseconds = firstMyDate.getMilliseconds() - secondMyDate.getMilliseconds();

        if (milliseconds < 0) {
            seconds--;
            milliseconds += 1000;
        }
        if (seconds < 0) {
            minutes--;
            seconds += 60;
        }
        if (minutes < 0) {
            hours--;
            minutes += 60;
        }
        if (hours < 0) {
            day--;
            hours += 24;
        }
        if (day < 1 && month == 2 && !isCurrentYearLeap(years)) {
            month--;
            day += 28;
        }
        if (day < 1 && month == 2 && isCurrentYearLeap(years)) {
            month--;
            day += 29;
        }
        if (day < 1 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            month--;
            day += 31;
        }
        if (day < 1 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
            month--;
            day += 30;
        }
        if (month < 1) {
            years--;
            month += 12;
        }
        if (years < 1) {
            throw new DateException("Извините, попробуйте повторно ввести год, больше, так как разница ниже минимального года");
        }

        myDate.setDay(day);
        myDate.setMonth(month);
        myDate.setYear(years);
        myDate.setHour(hours);
        myDate.setMinutes(minutes);
        myDate.setSeconds(seconds);
        myDate.setMilliseconds(milliseconds);
        return myDate;
    }


    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }
}