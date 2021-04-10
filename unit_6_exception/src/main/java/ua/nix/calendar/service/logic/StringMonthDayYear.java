package ua.nix.calendar.service.logic;

import ua.nix.calendar.exceptions.impl.DateException;

import java.util.Map;

public class StringMonthDayYear {
    private static Map<String, Integer> monthMap = Map.ofEntries(
            Map.entry("������", 1),
            Map.entry("�������", 2),
            Map.entry("����", 3),
            Map.entry("������", 4),
            Map.entry("���", 5),
            Map.entry("����", 6),
            Map.entry("����", 7),
            Map.entry("������", 8),
            Map.entry("��������", 9),
            Map.entry("�������", 11),
            Map.entry("������", 12),
            Map.entry("�������", 13));

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

    public static String[] createStringData(String result) throws DateException {
        String[] array = new String[7];
        String[] temp = result.split("[/ :]");
        if (temp[1].isEmpty()) {
            temp[1] = "01";
        }
        if (temp[2].isEmpty()) {
            temp[2] = "2021";
        }
        array[0] = temp[1];
        array[1] = String.valueOf(monthMap.get(temp[0]));;
        if (array[1] == "null") {
            throw new DateException("�� ����� �������� �����");
        }
        for (int i = 2; i < array.length; i++) {
            array[i] = temp[i];
        }
        return array;
    }
}
