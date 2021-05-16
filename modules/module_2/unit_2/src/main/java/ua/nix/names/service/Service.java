package ua.nix.names.service;

import ua.nix.names.generator.Generate;

import java.util.*;

public class Service {
    private static Service instance;

    public String start() {
        List<String> names = Generate.getInstance().create();
        System.out.println("Список имён : " + "\n" + names);
        String s = "Нет уникальных имён";
        for (int outerIndex = 0; outerIndex < names.size(); outerIndex++) {
            for (int innerIndex = 0; innerIndex < names.size(); innerIndex++) {
                if (names.get(outerIndex).equals(names.get(innerIndex))) {
                    if (outerIndex != innerIndex) {
                        break;
                    }
                } else {
                    if (innerIndex == names.size() - 1) {
                        return names.get(outerIndex);
                    }
                }
            }
        }
        return s;
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
}
