package ua.nix.calendar.service.logic;

import ua.nix.calendar.exceptions.impl.DateException;

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
        for (int z = 0; z < temp.length; z++) {
            array[z] = temp[z];
        }
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || i == 1 || i == 2) {
                array[i] += " ";
            } else if (i < array.length - 1) {
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
        array[0] = temp[1];
        array[1] = String.valueOf(monthMap.get(temp[0]));;
        if (array[1] == "null") {
            throw new DateException("Вы ввели неверный месяц");
        }
        for (int i = 2; i < array.length; i++) {
            array[i] = temp[i];
        }
        return array;
    }
}
