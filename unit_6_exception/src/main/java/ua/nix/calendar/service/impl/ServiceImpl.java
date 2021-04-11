package ua.nix.calendar.service.impl;

import ua.nix.calendar.data.DataBase;
import ua.nix.calendar.entity.MyDate;
import ua.nix.calendar.exceptions.impl.DateException;
import ua.nix.calendar.repository.impl.RepositoryImpl;
import ua.nix.calendar.service.Service;
import ua.nix.calendar.service.logic.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceImpl implements Service {
    private static ServiceImpl instance;

    private ServiceImpl() {
    }

    @Override
    public void createDate(String input) throws DateException {

        Matcher dayMonthYearWithoutTime = Pattern.compile("\\d{0,2}/\\d{1,2}/\\d{0,2}").matcher(input);
        Matcher dayMonthYearWithTime = Pattern.compile("\\d{0,2}/\\d{1,2}/\\d{0,2}\\s\\d{1,2}:?\\d{0,2}:?\\d{0,2}:?\\d{0,2}").matcher(input);
        Matcher monthDayYearWithoutTime = Pattern.compile("\\d{0,2}/\\d{0,2}/\\d{4}").matcher(input);
        Matcher monthDayYearWithTime = Pattern.compile("\\d{0,2}/\\d{0,2}/\\d{4}\\s\\d{1,2}:?\\d{0,2}:?\\d{0,2}:?\\d{0,2}").matcher(input);
        Matcher dayStringMonthYearWithoutTime = Pattern.compile("\\d{0,2}/\\w+/\\d{0,4}", Pattern.UNICODE_CHARACTER_CLASS).matcher(input);
        Matcher dayStringMonthYearWithTime = Pattern.compile("\\d{0,2}/\\w+/\\d{0,4}\\s\\d{1,2}:?\\d{0,2}:?\\d{0,2}:?\\d{0,2}", Pattern.UNICODE_CHARACTER_CLASS).matcher(input);
        Matcher stringMonthDayYearWithoutTime = Pattern.compile("\\w+/\\d{0,2}/\\d{0,4}", Pattern.UNICODE_CHARACTER_CLASS).matcher(input);
        Matcher monthStringDayYearWithTime = Pattern.compile("\\w+/\\d{0,2}/\\d{0,4}\\s\\d{1,2}:?\\d{0,2}:?\\d{0,2}:?\\d{0,2}", Pattern.UNICODE_CHARACTER_CLASS).matcher(input);

        try {
            if (dayMonthYearWithoutTime.matches()) {
                RepositoryImpl.create(DayMonthYearNoTime.createStringData(DayMonthYearNoTime.addTime(input)));
            } else if (dayMonthYearWithTime.matches()) {
                RepositoryImpl.create(DayMonthYear.createStringData(DayMonthYear.addTime(input)));
            } else if (monthDayYearWithoutTime.matches()) {
                RepositoryImpl.create(MonthDayYearNoTime.createStringData(MonthDayYearNoTime.addTime(input)));
            } else if (monthDayYearWithTime.matches()) {
                RepositoryImpl.create(MonthDayYear.createStringData(MonthDayYear.addTime(input)));
            } else if (dayStringMonthYearWithoutTime.matches()) {
                RepositoryImpl.create(DayStringMonthYearNoTime.createStringData(DayStringMonthYearNoTime.addTime(input)));
            } else if (dayStringMonthYearWithTime.matches()) {
                RepositoryImpl.create(DayStringMonthYear.createStringData(DayStringMonthYear.addTime(input)));
            } else if (stringMonthDayYearWithoutTime.matches()) {
                RepositoryImpl.create(StringMonthDayYearNoTime.createStringData(StringMonthDayYearNoTime.addTime(input)));
            } else if (monthStringDayYearWithTime.matches()) {
                RepositoryImpl.create(StringMonthDayYear.createStringData(StringMonthDayYear.addTime(input)));
            } else {
                throw new DateException("Pattern Error");
            }
        } catch (DateException d) {
            throw new DateException(d.getMessage());
        }
    }

    public static ServiceImpl getInstance() {
        if (instance == null) {
            instance = new ServiceImpl();
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        getInstance().createDate("4/6/21 21:00:00");
        getInstance().createDate("30/6/78 21:00:00");
        MyDate myDate1 = DataBase.getInstance().listMyDate.get(0);
        MyDate myDate2 = DataBase.getInstance().listMyDate.get(1);
        System.out.println(DataBase.getInstance().differenceBetweenTwoDate(myDate1, myDate2));
        System.out.println(DataBase.getInstance().addTwoDate(myDate1, myDate2));
        getInstance().createDate("05/22/2020 01:13:00");
        getInstance().createDate("01/31/1000 21:00:00");
        myDate1 = DataBase.getInstance().listMyDate.get(2);
        myDate2 = DataBase.getInstance().listMyDate.get(3);
        System.out.println(DataBase.getInstance().subtractTwoDate(myDate1, myDate2));
        Collections.sort(DataBase.getInstance().listMyDate);
        Collections.sort(DataBase.getInstance().listMyDate, Collections.reverseOrder());
        System.out.println(DataBase.getInstance().listMyDate);


    }
}