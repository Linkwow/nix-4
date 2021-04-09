package ua.nix.calendar.service.logic;

import ua.nix.calendar.exceptions.impl.DateExceptions;

public class DayMonthYearNoTime {

    private static final String DEFAULT_MILLENNIUM_AND_CENTURY = "19";

    public static String addTime(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.append(" 00:00:00:00");
        return sb.toString();
    }

    public static String[] createStringData(String result) {
        String[] array = new String[7];
        String[] temp = result.split("[/ :]");
        if(temp[0].isEmpty()) {
            temp[0] = "01";
        } if(temp[2].isEmpty()){
            temp[2] = "2021";
        } else if (temp[2].length() == 2) {
            String s;
            s = temp[2];
            temp[2] = DEFAULT_MILLENNIUM_AND_CENTURY;
            temp[2] += s;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = temp[i];
        }
        return array;
    }


}
