package ua.nix.datestandart.service.fileiteractions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.nix.datestandart.entity.RandomDate;

public class FileWrite {
    private static FileWrite instance;
    private static final File filePath = new File("input.json");

    public void createFile(List<RandomDate> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(filePath, list);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public static FileWrite getInstance() {
        if (instance == null) {
            instance = new FileWrite();
        }
        return instance;
    }
}
