package ua.nix.calendar.util;

import ua.nix.calendar.exceptions.impl.DateException;

public class UtilClass {
    public static final Long[] dayInMonth = new Long[]{31L, 28L, 31L, 30L, 31L, 30L, 31L, 31L, 30L, 31L, 30L, 31L};
    public static final String[] monthInYear = new String[]{"", "январь", "‘евраль", "ћарт", "јпрель", "ћай", "»юнь", "»юль", "јвгуст", "—ент€брь", "ќкт€брь",
            "Ќо€брь", "ƒекабрь"};
    public static final Long SECONDS_TO_MS_VALUE = 1000L;
    public static final Long MINUTE_TO_MS_VALUE = 60000L;
    public static final Long HOUR_TO_MS_VALUE = 3600000L;
    public static final Long DAY_TO_MS_VALUE = 86400000L;
    public static final Long DAY_IN_YEAR = 365L;

    public static Long[] stringArrayConvertToLongArray(String[] data) {
        Long[] arrayOfValue = new Long[7];
        for (int i = 0; i < data.length; i++) {
            arrayOfValue[i] = Long.parseLong(data[i]);
        }
        return arrayOfValue;
    }

    public static boolean isCurrentYearLeap(Long year) {
        boolean leapOrNot = false;
        if (year > 4 && year <= 1582) {
            if (year % 4 == 0) {
                leapOrNot = true;
            }
        } else {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    leapOrNot = true;
                }
            } else {
                if (year % 4 == 0) {
                    leapOrNot = true;
                }
            }
        }
        return leapOrNot;
    }

    public static Long dayPassedSinceYearStart(Long month) {
        long day = 0L;
        for (int i = 0; i < month - 1; i++) {
            day += dayInMonth[i];
        }
        return day;
    }

    public static Long monthToDay(Long month) {
        long day = 0L;
        for (int i = 0; i < month; i++) {
            day += dayInMonth[i];
        }
        return day;
    }


    public static long dayToMonth(long days){
        long monthCount = 0;
        long dayQuantity = days;
        for (int i = 0; i < dayInMonth.length; i++) {
            if(dayQuantity < dayInMonth[i]){
                break;
            } else{
                dayQuantity = dayQuantity - dayInMonth[i];
                monthCount++;
            }
        }
        return monthCount;
    }

    public static Long leapYearCount(Long year) {
        Long countYear = 0L;
        for (long i = 1; i <= year; i++) {
            if (isCurrentYearLeap(i)) {
                countYear++;
            }
        }
        return countYear;
    }

    public static Long leapYearCount(Long yearStart, long yearEnd) {
        long start, end = 0;
        if(yearStart > yearEnd){
            start = yearEnd;
            end = yearStart;
        } else {
            start = yearStart;
            end = yearEnd;
        }
        Long countYear = 0L;
        for (long i = start; i <= end; i++) {
            if (isCurrentYearLeap(i)) {
                countYear++;
            }
        }
        return countYear;
    }

    public static Boolean correctValueCheck(String[] arrayOfParameters) throws DateException {
        try {
            Long[] checkArrayOfParameters = parseStringToLongAndCheck(arrayOfParameters);
            Boolean leapYear = isCurrentYearLeap(checkArrayOfParameters[2]);
            checkDay(checkArrayOfParameters[0], checkArrayOfParameters[1], leapYear);
            checkMonth(checkArrayOfParameters[1]);
            checkYear(checkArrayOfParameters[2]);
            checkHours(checkArrayOfParameters[3]);
            checkMinutes(checkArrayOfParameters[4]);
            checkSeconds(checkArrayOfParameters[5]);
            checkMilliSeconds(checkArrayOfParameters[6]);
            return leapYear;
        } catch (DateException exception) {
            throw new DateException(exception.getMessage());
        }
    }

    private static Long[] parseStringToLongAndCheck(String[] arrayOfParameters) throws DateException {
        Long[] temp = new Long[7];
        try {
            for (int i = 0; i < arrayOfParameters.length; i++) {
                temp[i] = Long.parseLong(arrayOfParameters[i]);
            }
        } catch (Exception e) {
            throw new DateException("»звините, вы ввели дату не соответствующую шаблону, попробуйте повторно");
        }
        return temp;
    }

    private static void checkDay(Long day, Long month, Boolean leapYear) throws DateException {
        if (day > 31 || (day < 1)) {
            throw new DateException("»звините, попробуйте повторно ввести день в диапазоне не больше 31 и не меньше 1.");
        } else if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            throw new DateException("»звините, количество дней не должно быть 31 в этом мес€це, попробуйте повторно ввести день.");
        } else if (day == 30 && (month == 1 || month == 2 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
            throw new DateException("»звините, количество дней не должно быть 30 в этом мес€це, попробуйте повторно ввести день.");
        } else if (month == 2 && !leapYear && day == 29) {
            throw new DateException("»звините, количество дней не должно быть 29 в этом мес€це, попробуйте повторно ввести день.");
        }
    }

    private static void checkMonth(Long month) throws DateException {
        if(month < 1 || month > 12){
            throw new DateException("»звините, попробуйте повторно ввести мес€ц в диапазоне не больше 12 и не меньше 1.");
        }
    }

    private static void checkYear(Long year) throws DateException {
        if(year < 1){
            throw new DateException("»звините, попробуйте повторно ввести год не меньше 1.");
        }
    }

    private static void checkHours(Long hours) throws DateException{
        if(hours > 23){
            throw new DateException("»звините, попробуйте повторно ввести часы в диапазоне не больше 23.");
        }
    }

    private static void checkMinutes(Long minutes) throws DateException{
        if(minutes > 59){
            throw new DateException("»звините, попробуйте повторно ввести минуты в диапазоне не больше 59.");
        }
    }

    private static void checkSeconds(Long seconds) throws DateException{
        if(seconds > 59){
            throw new DateException("»звините, попробуйте повторно ввести секунды в диапазоне не больше 59.");
        }
    }

    private static void checkMilliSeconds(Long milliSeconds) throws DateException{
        if(milliSeconds > 999){
            throw new DateException("»звините, попробуйте повторно ввести секунды в диапазоне не больше 999.");
        }
    }

    public static String returnStringRepresentationOfMonth(long month){
        String s = "";
        for (int i = 0; i < monthInYear.length; i++) {
            if(i == month){
                s = monthInYear[i];
            }
        }
        return s;
    }

}