package ua.nix.app;

import ua.nix.libs.csvdatastore.CSVDataStore;
import ua.nix.libs.csvmapper.CSVMapper;
import ua.nix.app.demonstration.annotatons.*;
import ua.nix.app.demonstration.objects.*;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        CSVDataStore csvDataStore = new CSVDataStore(App.class);
        try {
            csvDataStore.readFile("person.csv");
            csvDataStore.parseFile(",");
            CSVMapper<Person> csvMapper = new CSVMapper<>(Person.class, csvDataStore, PersonAnnotation.class);
            for (Person person : csvMapper.getObjectList()){
                System.out.println(person);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}