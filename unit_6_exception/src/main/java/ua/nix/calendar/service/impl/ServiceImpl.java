package ua.nix.calendar.service.impl;

import ua.nix.calendar.data.DataBase;
import ua.nix.calendar.entity.Date;
import ua.nix.calendar.exceptions.impl.DateException;
import ua.nix.calendar.repository.impl.RepositoryImpl;
import ua.nix.calendar.service.Service;
import ua.nix.calendar.service.logic.*;

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
        getInstance().createDate("1/12/21 10:00:00");
        getInstance().createDate("1/12/20 00:00:00");
        Date date1 = DataBase.getInstance().listDate.get(0);
        Date date2 = DataBase.getInstance().listDate.get(1);
        System.out.println(DataBase.getInstance().subtractTwoDate(date1, date2));

    }
}