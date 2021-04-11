package ua.nix.calendar.service.logic;

import ua.nix.calendar.exceptions.DateException;

import java.util.Map;

public class StringMonthDayYearNoTime {

    private static final String DEFAULT_MILLENNIUM_AND_CENTURY = "19";

    private static final Map<String, Integer> monthMap = Map.ofEntries(
            Map.entry("������", 1),
            Map.entry("�������", 2),
            Map.entry("����", 3),
            Map.entry("������", 4),
            Map.entry("���", 5),
            Map.entry("����", 6),
            Map.entry("����", 7),
            Map.entry("������", 8),
            Map.entry("��������", 9),
            Map.entry("�������", 10),
            Map.entry("������", 11),
            Map.entry("�������", 12));

    public static String addTime(String input) {
        return input + " 00:00:00:00";
    }

    public static String[] createStringData(String result) throws DateException {
        String[] array = new String[7];
        String[] temp = result.split("[/ :]");
        if (temp[1].isEmpty()) {
            temp[1] = "01";
        }
        if (temp[2].isEmpty()) {
            temp[2] = "2021";
        } else if (temp[2].length() == 2) {
            String s;
            s = temp[2];
            temp[2] = DEFAULT_MILLENNIUM_AND_CENTURY;
            temp[2] += s;
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
