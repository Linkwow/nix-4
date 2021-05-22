package ua.nix.pathroute.service.fileinteractions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private final File file = new File("graphapp\\src\\main\\resources\\output.txt");
    private static Writer instance;

    public void writeFile(String output) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(output);
            writer.write(" ");
            writer.close();
        } catch (IOException ioException){
            System.err.println("Файл не был создан.");
        }
    }

    public static Writer getInstance(){
        if(instance == null){
            instance = new Writer();
        }
        return instance;
    }
}
