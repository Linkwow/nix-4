package ua.nix.app;

import ua.nix.libs.csvdatastore.CSVDataStore;
import ua.nix.libs.csvmapper.CSVMapper;
import ua.nix.app.demonstration.annotatons.*;
import ua.nix.app.demonstration.objects.*;

import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        CSVDataStore csvDataStore = new CSVDataStore(App.class, "person.csv");
        csvDataStore.readFile();
        csvDataStore.parseFile(",");
        CSVMapper<Person> csvMapper = new CSVMapper<>(Person.class, csvDataStore, PersonAnnotation.class);
        try {
            csvMapper.mapping();
        } catch (Exception exception){
            exception.printStackTrace();
            System.err.println(exception.getMessage());
        }
        List<Person> personList = csvMapper.getObjectList();
        for (Person person : personList){
            System.out.println(person);
        }
    }
}
