package ua.nix.calendar.service.logic;

import ua.nix.calendar.exceptions.impl.DateExceptions;

public class ConvertStringToLong {

    private static final long[] dayInMonth = new long[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final long SECONDS_TO_MS_VALUE = 1000;
    private static final long MINUTE_TO_MS_VALUE = 60000;
    private static final long HOUR_TO_MS_VALUE = 3600000;
    private static final long DAY_TO_MS_VALUE = 86400000;

    public static long[] dataPrepareToSave(String[] data) throws DateExceptions{ //принимает УПОРЯДОЧЕННУЮ по dd/mm/yy hh:mm:ss:ms
        try {
            correctValueCheck(data);
        } catch (DateExceptions d){
            System.err.println(d.getMessage());
            throw new DateExceptions();
        }

        long[] arrayOfValue = new long[8];
        for (int i = 0; i < data.length; i++) {
            if (i < 3) {
                arrayOfValue[i] = (Long.parseLong(data[i]) - 1);
            } else {
                arrayOfValue[i] = Long.parseLong(data[i]);
            }
        }

        long dayPassedBeforeThisDate = arrayOfValue[2] * 365 + leapYearCount(arrayOfValue[2] + 1) + dayInMonthCount(arrayOfValue[1]) + arrayOfValue[0];


        if (isCurrentYearLeap(arrayOfValue[2] + 1) && arrayOfValue[1] >= 2) {
            dayPassedBeforeThisDate++;
        }

        long time = arrayOfValue[3] * HOUR_TO_MS_VALUE + arrayOfValue[4] * MINUTE_TO_MS_VALUE + arrayOfValue[5] * SECONDS_TO_MS_VALUE + arrayOfValue[6];
        long ms = dayPassedBeforeThisDate * DAY_TO_MS_VALUE + time;
        arrayOfValue[7] = ms;
        return arrayOfValue;
    }

    private static boolean isCurrentYearLeap(long year) {
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

    private static long dayInMonthCount(long month) {
        long count = 0;
        for (int i = 0; i < month; i++) {
            count += dayInMonth[i];
        }
        return count;
    }

    private static long leapYearCount(long year) {
        long count = 0;
        for (int i = 1; i < year; i++) {
            if (isCurrentYearLeap(i)) {
                count++;
            }
            if (i % 128 == 0) {
                count--;
            }
        }
        return count;
    }

    private static void correctValueCheck(String[] data) throws DateExceptions {
        long[] temp = new long[8];
        for (int i = 0; i < data.length; i++) {
            temp[i] = Long.parseLong(data[i]);
        }
        boolean leapYear = isCurrentYearLeap(temp[2]);
        if (temp[0] > 31) {
            throw new DateExceptions("Произошёл ввод недопустимого количества дней, попробуйте ввести не больше 31 дня");
        } else if (temp[0] == 31 && (temp[1] == 4 || temp[1] == 6 || temp[1] == 9 || temp[1] == 11)) {
            throw new DateExceptions("Произошёл ввод недопустимого количества дней, Количестов дней должно быть 30");
        } else if (temp[0] == 0) {
            throw new DateExceptions();
        } else if (temp[1] == 2) {
            if (leapYear) {
                if (temp[0] > 29) {
                    throw new DateExceptions();
                }
            } else {
                if (temp[0] > 28) {
                    throw new DateExceptions();
                }
            }
        } else if (temp[1] > 12 || temp[1] < 1){
            throw new DateExceptions();
        }
    }
}
