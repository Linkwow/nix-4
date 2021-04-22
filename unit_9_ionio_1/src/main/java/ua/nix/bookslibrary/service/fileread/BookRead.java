package ua.nix.bookslibrary.service.fileread;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BookRead {

    private List<String[]> data;
    private final String file = "books.csv";
    private static BookRead instance;

    private BookRead(){}

    public List<String[]> read(){
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            data = reader.readAll();
        } catch (CsvException csvException){
            System.err.println(csvException.getMessage());
        } catch (IOException exception){
            System.err.println(exception.getMessage());
        }
        return data;
    }

    public static BookRead getInstance(){
        if(instance == null){
            instance = new BookRead();
        }
        return instance;
    }

}
