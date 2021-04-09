package ua.nix.calendar.service.logic;

public class MonthDayYear {

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
