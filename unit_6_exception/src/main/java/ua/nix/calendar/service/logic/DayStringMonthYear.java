package ua.nix.calendar.service.logic;

import ua.nix.calendar.exceptions.DateException;

import java.util.Map;

public class DayStringMonthYear {
    private static Map<String, Integer> monthMap = Map.ofEntries(
            Map.entry("Январь", 1),
            Map.entry("Февраль", 2),
            Map.entry("Март", 3),
            Map.entry("Апрель", 4),
            Map.entry("Май", 5),
            Map.entry("Июнь", 6),
            Map.entry("Июль", 7),
            Map.entry("Август", 8),
            Map.entry("Сентябрь", 9),
            Map.entry("Октябрь", 11),
            Map.entry("Ноябрь", 12),
            Map.entry("Декабрь", 13));

    public static String addTime(String input) {
        String[] array = new String[]{"", "", "", "", "", "", ""};
        String[] temp = input.split("[ :]");
        StringBuilder sb = new StringBuilder();

        if (Integer.parseInt(temp[0]) > 31) {
            array[0] = "";
        } else {
            array[0] = temp[0];
        }
        for (String s : monthMap.keySet()){
            if (temp[1].equals(s)){
                array[1] = String.valueOf(monthMap.get(s));
            } else {
                array[1] ="";
            }
        }
        for (int i = 0, j = 2; i < temp.length; i++, j++) {
            array[j] = temp[i];
        }
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                if(array[i].isEmpty()){
                    array[i] = "01 ";
                } else {
                    array[i] += " ";
                }
            } else if (i == 1) {
                if (array[i].isEmpty()) {
                    array[i] += "01 ";
                } else {
                    array[i] += " ";
                }
            } else if(i == 2 ){
                    array[2] += " ";
            } else if (i == 3 || i == 4 || i == 5){
                if (array[i].isEmpty()) {
                    array[i] += "00:";
                } else {
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

    public static String[] createStringData(String result) throws DateException {
        String[] array = new String[7];
        String[] temp = result.split("[/ :]");
        for (int i = 0; i < array.length; i++) {
            array[i] = temp[i];
        }
        if (array[1] == "null") {
            throw new DateException("Вы ввели неверный месяц");
        }
        return array;
    }
}
