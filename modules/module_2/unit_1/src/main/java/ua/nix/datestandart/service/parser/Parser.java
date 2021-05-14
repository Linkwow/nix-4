package ua.nix.datestandart.service.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

import ua.nix.datestandart.service.checker.Checker;

public class Parser {
    private static Parser instance;
    private final static String nullData = "Вы не ввели валидных данных. Попробуйте снова";

    public String receive(String inputString) {
       List<String> inputData;
       if(inputString == null) {
           inputData = new ArrayList<>(Collections.singletonList(nullData));
       } else {
           inputData = new ArrayList<>(Arrays.asList(inputString.split("\\s*[,\\s]+")));
       }
        return Checker.getInstance().parseInputString(inputData);
    }

    public static Parser getInstance() {
        if(instance == null){
            instance = new Parser();
        }
        return instance;
    }
}