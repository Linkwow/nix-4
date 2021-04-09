package ua.nix.calendar.service.logic;

public class MonthDayYearNoTime {

    public static String addTime(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.append(" 00:00:00:00");
        return sb.toString();
    }

    public static String[] createStringData(String result) { // m/d/yyyy
        String[] array = new String[7];
        String[] temp = result.split("[/ :]");
        if (temp[0].isEmpty()) {
            temp[0] = "01";
        }
        if (temp[1].isEmpty()) {
            temp[1] = "01";
        }
        array[0] = temp[1];
        array[1] = temp[0];
        for (int i = 2; i < array.length; i++) {
            array[i] = temp[i];
        }
        return array;
    }
}
