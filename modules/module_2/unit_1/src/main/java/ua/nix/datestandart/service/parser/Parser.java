package ua.nix.datestandart.service.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class Parser {
    private static Parser instance;
    private final static String nullData = "Вы не ввели валидных данных. Попробуйте снова";

    public List<String> receive(String inputString) {
       if(inputString == null) {
           return new ArrayList<>(Collections.singletonList(nullData));
       } else {
           return new ArrayList<>(Arrays.asList(inputString.split("\\s*[,\\s]+")));
       }
    }

    public static Parser getInstance() {
        if(instance == null){
            instance = new Parser();
        }
        return instance;
    }

}
