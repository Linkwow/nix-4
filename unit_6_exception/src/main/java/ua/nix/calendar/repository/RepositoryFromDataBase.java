package ua.nix.calendar.repository;

import ua.nix.calendar.dao.impl.DaoImpl;
import ua.nix.calendar.entity.MyDate;
import ua.nix.calendar.exceptions.DateException;

import static ua.nix.calendar.util.UtilClass.*;

import java.util.List;

public class RepositoryFromDataBase {

    private static RepositoryFromDataBase instance;

    public void difference(int firstId, int secondId) throws DateException {
        String[] temp = differenceBetweenTwoDate(DaoImpl.getInstance().read(firstId), DaoImpl.getInstance().read(secondId));
        try {
            RepositoryToDataBaseImpl.create(temp);
        } catch (DateException d) {
            throw new DateException(d.getMessage());
        }
    }

    public void subtract(int firstId, int secondId) throws DateException {
        String[] temp = subtractTwoDate(DaoImpl.getInstance().read(firstId), DaoImpl.getInstance().read(secondId));
        try {
            RepositoryToDataBaseImpl.create(temp);
        } catch (DateException d) {
            throw new DateException(d.getMessage());
        }
    }

    public void add(int firstId, int secondId) throws DateException {
        String[] temp = addTwoDate(DaoImpl.getInstance().read(firstId), DaoImpl.getInstance().read(secondId));
        try {
            RepositoryToDataBaseImpl.create(temp);
        } catch (DateException d) {
            throw new DateException(d.getMessage());
        }
    }

    public void output(int choice) throws DateException {
        if(choice < 1 || choice > 4){
            throw new DateException("Извините, попробуйте повторно ввести номер формата для вывода.");
        }
        switch (choice) {
            case 1:
                firstFormatOutput();
                break;
            case 2:
                secondFormatOutput();
                break;
            case 3:
                thirdFormatOutput();
                break;
            case 4:
                fourthFormatOutput();
                break;
        }
    }

    private List<MyDate> getAll() {
        return DaoImpl.getInstance().getAll();
    }

    public void ascSort() throws DateException{
        try {
            DaoImpl.getInstance().ascSort();
        } catch (Exception e){
            throw new DateException("Извините но в списке нет элементов.");
        }
    }

    public void descSort() throws DateException {
        try {
        DaoImpl.getInstance().descSort();
        } catch (Exception e){
            throw new DateException("Извините но в списке нет элементов.");
        }
    }

    private void firstFormatOutput() {
        String day = "";
        for (MyDate d : getAll()) {
            if (d.getDay() <= 9) {
                day = "0" + d.getDay();
            } else {
                day = String.valueOf(d.getDay());
            }
            System.out.println(day + "/" +
                    d.getMonth() + "/" +
                    String.valueOf(d.getYear()).charAt(2) +
                    String.valueOf(d.getYear()).charAt(3));
        }
    }

    private void secondFormatOutput() {
        for (MyDate d : getAll()) {
            System.out.println(d.getMonth() + "/" +
                    d.getDay() + "/" +
                    d.getYear());
        }
    }

    private void thirdFormatOutput() {
        for (MyDate d : getAll()) {
            System.out.println(returnStringRepresentationOfMonth(d.getMonth()) + " " +
                    d.getDay() + " " +
                    String.valueOf(d.getYear()).charAt(2) +
                    String.valueOf(d.getYear()).charAt(3));
        }
    }

    private void fourthFormatOutput() {
        String day = "";
        for (MyDate date : getAll()) {
            if (date.getDay() <= 9) {
                day = "0" + date.getDay();
            }
            for (MyDate d : getAll()) {
                System.out.println(day + " " +
                        " " + returnStringRepresentationOfMonth(d.getMonth()) +
                        " " + d.getYear() +
                        " " + d.getHour() +
                        ":" + d.getMinutes() +
                        ":" + d.getSeconds() +
                        ":" + d.getMilliseconds());
            }
        }
    }

    private String[] differenceBetweenTwoDate(MyDate firstMyDate, MyDate secondMyDate) throws DateException {
        String[] array = new String[7];
        if(firstMyDate == null || secondMyDate == null){
            throw new DateException();
        }
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
        array[0] = String.valueOf(day);
        array[1] = String.valueOf(month);
        array[2] = String.valueOf(years);
        array[3] = String.valueOf(hours);
        array[4] = String.valueOf(minutes);
        array[5] = String.valueOf(seconds);
        array[6] = String.valueOf(milliseconds);
        System.out.println("Разница между двумя датами составляет :" +
                array[6] + " миллисекунд " +
                array[5] + " секунд " +
                array[4] + " минут " +
                array[3] + " часов " +
                array[0] + " дней " +
                array[1] + " месяцев " +
                array[2] + " лет " +
                years / 100 + 1 + " веков.");
        return array;
    }

    public String[] addTwoDate(MyDate firstMyDate, MyDate secondMyDate) throws DateException {
        if(firstMyDate == null || secondMyDate == null){
            throw new DateException();
        }
        String[] array = new String[7];
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
        array[0] = String.valueOf(day);
        array[1] = String.valueOf(month);
        array[2] = String.valueOf(years);
        array[3] = String.valueOf(hours);
        array[4] = String.valueOf(minutes);
        array[5] = String.valueOf(seconds);
        array[6] = String.valueOf(milliseconds);
        System.out.println("После сложения двух выбранных дат, дата составляет : " +
                array[6] + " миллисекунд " +
                array[5] + " секунд " +
                array[4] + " минут " +
                array[3] + " часов " +
                array[0] + " дней " +
                array[1] + " месяцев " +
                array[2] + " лет " +
                years / 100 + 1 + " веков.");
        return array;
    }

    public String[] subtractTwoDate(MyDate firstMyDate, MyDate secondMyDate) throws DateException {
        String[] array = new String[7];
        if(firstMyDate == null || secondMyDate == null){
            throw new DateException();
        }
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
            throw new DateException("Извините, попробуйте повторно выбрать данные, так как разница ниже минимального года");
        }

        array[0] = String.valueOf(day);
        array[1] = String.valueOf(month);
        array[2] = String.valueOf(years);
        array[3] = String.valueOf(hours);
        array[4] = String.valueOf(minutes);
        array[5] = String.valueOf(seconds);
        array[6] = String.valueOf(milliseconds);
        System.out.println("После вычитания двух выбранных дат, дата составляет : " +
                array[6] + " миллисекунд " +
                array[5] + " секунд " +
                array[4] + " минут " +
                array[3] + " часов " +
                array[0] + " дней " +
                array[1] + " месяцев " +
                array[2] + " лет " +
                years / 100 + 1 + " веков.");
        return array;
    }

    public static RepositoryFromDataBase getInstance() {
        if(instance == null){
            instance = new RepositoryFromDataBase();
        }
        return instance;
    }
}