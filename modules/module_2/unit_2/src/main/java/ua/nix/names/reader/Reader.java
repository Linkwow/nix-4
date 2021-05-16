package ua.nix.names.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static Reader instance;
    private final List<String> names = new ArrayList<>();

    public List<String> readOutput() {
        File outputFile = new File("src\\main\\resources\\names.txt");
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(outputFile));
            while ((s = br.readLine()) != null) {
                names.add(s);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Программа не может прочитать файл. Проверьте правильность введенного пути");
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
        return names;
    }

    public static Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }
}
