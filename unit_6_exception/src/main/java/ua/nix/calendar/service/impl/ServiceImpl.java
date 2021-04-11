package ua.nix.calendar.service.impl;

import ua.nix.calendar.exceptions.impl.DateException;
import ua.nix.calendar.repository.RepositoryFromDataBase;
import ua.nix.calendar.repository.RepositoryToDataBaseImpl;
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
                RepositoryToDataBaseImpl.create(DayMonthYearNoTime.createStringData(DayMonthYearNoTime.addTime(input)));
            } else if (dayMonthYearWithTime.matches()) {
                RepositoryToDataBaseImpl.create(DayMonthYear.createStringData(DayMonthYear.addTime(input)));
            } else if (monthDayYearWithoutTime.matches()) {
                RepositoryToDataBaseImpl.create(MonthDayYearNoTime.createStringData(MonthDayYearNoTime.addTime(input)));
            } else if (monthDayYearWithTime.matches()) {
                RepositoryToDataBaseImpl.create(MonthDayYear.createStringData(MonthDayYear.addTime(input)));
            } else if (dayStringMonthYearWithoutTime.matches()) {
                RepositoryToDataBaseImpl.create(DayStringMonthYearNoTime.createStringData(DayStringMonthYearNoTime.addTime(input)));
            } else if (dayStringMonthYearWithTime.matches()) {
                RepositoryToDataBaseImpl.create(DayStringMonthYear.createStringData(DayStringMonthYear.addTime(input)));
            } else if (stringMonthDayYearWithoutTime.matches()) {
                RepositoryToDataBaseImpl.create(StringMonthDayYearNoTime.createStringData(StringMonthDayYearNoTime.addTime(input)));
            } else if (monthStringDayYearWithTime.matches()) {
                RepositoryToDataBaseImpl.create(StringMonthDayYear.createStringData(StringMonthDayYear.addTime(input)));
            } else {
                throw new DateException("Извините попробуйте снова ввести Вашу строку в соответствии с форматом");
            }
        } catch (DateException d) {
            throw new DateException(d.getMessage());
        }
    }

    public void subtract(int firstId, int secondId) throws DateException {
        RepositoryFromDataBase.getInstance().subtract(firstId, secondId);
    }

    public void difference(int firstId, int secondId) throws DateException{
        RepositoryFromDataBase.getInstance().difference(firstId, secondId);
    }

    public void add(int firstId, int secondId) throws DateException {
        RepositoryFromDataBase.getInstance().add(firstId, secondId);
    }

    public void output(int choice) throws DateException {
        try{
            RepositoryFromDataBase.getInstance().output(choice);
        } catch (DateException d){
            throw new DateException(d.getMessage());
        }
    }

    public void ascSort() throws DateException{
        try {
            RepositoryFromDataBase.getInstance().ascSort();
        } catch (DateException d){
            throw new DateException(d.getMessage());
        }
    }

    public void descSort() throws DateException{
        try {
        RepositoryFromDataBase.getInstance().descSort();
        } catch (DateException d){
            throw new DateException(d.getMessage());
        }
    }

    public static ServiceImpl getInstance() {
        if (instance == null) {
            instance = new ServiceImpl();
        }
        return instance;
    }
}