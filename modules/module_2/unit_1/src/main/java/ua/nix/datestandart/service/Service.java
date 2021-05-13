package ua.nix.datestandart.service;

import java.util.ArrayList;
import java.util.List;

import ua.nix.datestandart.entity.RandomDate;
import ua.nix.datestandart.service.fileiteractions.FileWrite;
import ua.nix.datestandart.service.parser.Parser;

public class Service {
    private static Service instance;

    public void createInputDataFile(int count){
        sendData(generateRandomData(count));
    }

    public void parseFile(){}


    public void parseFile(String inputFromFile){
        Parser.getInstance().receive(inputFromFile);
    }

    private List<RandomDate> generateRandomData(int count){
        List<RandomDate> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new RandomDate());
        }
        return list;
    }

    private void sendData(List<RandomDate> objectsToWrite){
        FileWrite.getInstance().createFile(objectsToWrite);
    }

    public static Service getInstance() {
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }

    public static void main(String[] args) {
        getInstance().createInputDataFile();
    }
}
