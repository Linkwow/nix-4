package ua.nix.calendar.service.logic;

public class DayMonthYear {

    private static final String DEFAULT_MILLENNIUM_AND_CENTURY = "19";

    public static String addTime(String input) {
        String[] array = new String[]{"", "", "", "", ""};
        String[] temp = input.split("[ :]");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            array[i] = temp[i];
        }
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                array[i] += " ";
            } else if (i == 1 || i == 2 || i == 3) {
                if (array[i].isEmpty()) {
                    array[i] += "00:";
                } else{
                    array[i] += ":";
                }
            } else {
                if (array[i].isEmpty()) {
                    array[i] += "00";
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static String[] createStringData(String result) {
        String[] array = new String[7];
        String[] temp = result.split("[/ :]");
        if (temp[0].isEmpty()) {
            temp[0] = "01";
        }
        if (temp[2].isEmpty()) {
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
