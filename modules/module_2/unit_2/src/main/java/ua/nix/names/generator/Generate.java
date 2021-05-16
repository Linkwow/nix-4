package ua.nix.names.generator;

import ua.nix.names.reader.Reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Generate {
    private final Random rand = new Random(new Date().getTime());
    private static Generate instance;

    public List<String> create() {
        List<String> names = Reader.getInstance().readOutput();
        List<String> listOfNames = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            listOfNames.add(names.get(rand.nextInt(names.size())));
        }
        return listOfNames;
    }

    public static Generate getInstance() {
        if(instance == null){
            instance = new Generate();
        }
        return instance;
    }
}
