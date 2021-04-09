package ua.nix.calendar.service.logic;

import ua.nix.calendar.exceptions.impl.DateExceptions;

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

    public static String[] createStringData(String result) throws DateExceptions {
        String[] array = new String[7];
        String[] temp = result.split("[/ :]");
        if (temp[0].isEmpty()) {
            temp[0] = "01";
        }
        if (temp[2].isEmpty()) {
            temp[2] = "2021";
        }
        try {
            for (int i = 0; i < array.length; i++) {
                if (i == 1) {
                    array[i] = String.valueOf(monthMap.get(temp[i]));
                    if (array[i] == "null") {
                        throw new DateExceptions("Вы ввели неверный месяц");
                    }
                } else {
                    array[i] = temp[i];
                }
            }
        } catch (DateExceptions d) {
            throw new DateExceptions(d.getMessage());
        }
        return array;
    }
}
