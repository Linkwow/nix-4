package ua.nix.calendar.service.impl;

import ua.nix.calendar.data.DataBase;
import ua.nix.calendar.exceptions.impl.DateExceptions;

import static ua.nix.calendar.service.logic.ConvertStringToLong.*;

import ua.nix.calendar.service.logic.*;
import ua.nix.calendar.service.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceImpl implements Service {
    private static ServiceImpl instance;

    private ServiceImpl() {
    }

    @Override
    public void createDate(String input) throws DateExceptions {
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
                DataBase.getInstance().create(dataPrepareToSave(DayMonthYearNoTime.createStringData(DayMonthYearNoTime.addTime(input))));
            } else if (dayMonthYearWithTime.matches()) {
                DataBase.getInstance().create(dataPrepareToSave(DayMonthYear.createStringData(DayMonthYear.addTime(input))));
            } else if (monthDayYearWithoutTime.matches()) {
                DataBase.getInstance().create(dataPrepareToSave(MonthDayYearNoTime.createStringData(MonthDayYearNoTime.addTime(input))));
            } else if (monthDayYearWithTime.matches()) {
                DataBase.getInstance().create(dataPrepareToSave(MonthDayYear.createStringData(MonthDayYear.addTime(input))));
            } else if (dayStringMonthYearWithoutTime.matches()) {
                DataBase.getInstance().create(dataPrepareToSave(DayStringMonthYearNoTime.createStringData(DayStringMonthYearNoTime.addTime(input))));
            } else if (dayStringMonthYearWithTime.matches()) {
                DataBase.getInstance().create(dataPrepareToSave(DayStringMonthYear.createStringData(DayStringMonthYear.addTime(input))));
            } else if (stringMonthDayYearWithoutTime.matches()) {
                DataBase.getInstance().create(dataPrepareToSave(StringMonthDayYearNoTime.createStringData(StringMonthDayYearNoTime.addTime(input))));
            } else if (monthStringDayYearWithTime.matches()) {
                DataBase.getInstance().create(dataPrepareToSave(StringMonthDayYear.createStringData(StringMonthDayYear.addTime(input))));
            } else {
                throw new DateExceptions("Pattern Error");
            }
        } catch (DateExceptions d) {
            System.err.println(d.getMessage());
        }
    }

    public static ServiceImpl getInstance() {
        if (instance == null) {
            instance = new ServiceImpl();
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        getInstance().createDate("/04/");
        getInstance().createDate("01/04/");
        getInstance().createDate("/04/02");
        getInstance().createDate("12/03/21");
        getInstance().createDate("1/12/21 12:00:00");
        getInstance().createDate("//2021");
        getInstance().createDate("12//2021");
        getInstance().createDate("12//2021 12");
        getInstance().createDate("//2021 00:47");
        getInstance().createDate("1/Май/102");
        getInstance().createDate("/Февраль/ 12");
        getInstance().createDate("Февраль//");
        getInstance().createDate("Февраль// 12");

    }
}