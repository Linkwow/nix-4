package ua.nix.datestandart.service.checker;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    private static Checker instance;

    public String parseInputString(List<String> inputData) {
        StringBuilder sb = new StringBuilder();
        String result;
        for (String date : inputData) {
            result = checkForPatternCorrespond(date);
            if (!result.isBlank()) {
                sb.append(checkForPatternCorrespond(date));
                sb.append(" ");
            }
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private String checkForPatternCorrespond(String date) {
        StringBuilder sb = new StringBuilder();
        String s;
        Matcher yearMonthDay = Pattern.compile("(\\d{4}/)(\\d{2}/)(\\d{2})").matcher(date);
        Matcher dayMonthYear = Pattern.compile("(\\d{2}/)(\\d{2}/)(\\d{4})").matcher(date);
        Matcher monthDayYear = Pattern.compile("(\\d{2}-)(\\d{2}-)(\\d{4})").matcher(date);
        if (yearMonthDay.matches()) {
            sb.append(yearMonthDay.group(1));
            sb.append(yearMonthDay.group(2));
            sb.append(yearMonthDay.group(3));
            s = sb.toString().replace("/", "");
            return valueOfRange(s);
        } else if (dayMonthYear.matches()) {
            sb.append(dayMonthYear.group(3));
            sb.append(dayMonthYear.group(2));
            sb.append(dayMonthYear.group(1));
            s = sb.toString().replace("/", "");
            return valueOfRange(s);
        } else if (monthDayYear.matches()) {
            sb.append(monthDayYear.group(3));
            sb.append(monthDayYear.group(1));
            sb.append(monthDayYear.group(2));
            s = sb.toString().replace("-", "");
            return valueOfRange(s);
        }
        return "";
    }

    private String valueOfRange(String value){
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        int month = Integer.parseInt(sb.substring(4, 6));
        int day = Integer.parseInt(sb.substring(6, 8));
        if( month > 12) {
            return "";
        }else if(day > 31){
            return "";
        }
        return value;
    }

    public static Checker getInstance() {
        if (instance == null) {
            instance = new Checker();
        }
        return instance;
    }
}
