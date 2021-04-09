package ua.nix.calendar.service.impl;

import ua.nix.calendar.data.DataBase;
import ua.nix.calendar.exceptions.impl.DateExceptions;
import static ua.nix.calendar.service.logic.ConvertStringToLong.*;

import ua.nix.calendar.service.logic.DayMonthYear;
import ua.nix.calendar.service.logic.DayMonthYearNoTime;
import ua.nix.calendar.service.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceImpl implements Service {
    private static ServiceImpl instance;

    private ServiceImpl() {}

    @Override
    public void createDate(String input) throws DateExceptions {
        Matcher dayMonthYearWithoutTime = Pattern.compile("\\d{0,2}/\\d{1,2}/\\d{0,2}").matcher(input);
        Matcher dayMonthYearWithTime = Pattern.compile("\\d{0,2}/\\d{1,2}/\\d{0,2}\\s\\d{1,2}:?\\d{0,2}:?\\d{0,2}:?\\d{0,2}").matcher(input);
        Matcher monthDayYear = Pattern.compile("\\d{0,2}/\\d{1,2}/\\d{0,2}\\s\\d{1,2}:?\\d{0,2}:?\\d{0,2}:?\\d{0,2}").matcher(input);
        try {
            if (dayMonthYearWithoutTime.matches()) {
                DataBase.getInstance().create(dataPrepareToSave(DayMonthYearNoTime.createStringData(DayMonthYearNoTime.addTime(input))));
            } else if (dayMonthYearWithTime.matches()) {
                DataBase.getInstance().create(dataPrepareToSave(DayMonthYear.createStringData(DayMonthYear.addTime(input))));
            } else {
                throw new DateExceptions("Pattern Error");
            }
        } catch (DateExceptions d){
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
        getInstance(). createDate("1/12/21 12:00:00");

    }
}