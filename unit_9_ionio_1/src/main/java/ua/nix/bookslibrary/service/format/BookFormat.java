package ua.nix.bookslibrary.service.format;

import java.util.Formatter;

public class BookFormat {
    private static BookFormat instance;

    private BookFormat(){}

    public String setIdFormat(String id){
        Formatter formatter = new Formatter();
        return formatter.format("%-5s", id).toString();
    }

    public String setTitleFormat(String name){
        Formatter formatter = new Formatter();
        return formatter.format("%-15s", name).toString();
    }

    public String setAuthorsFormat(String books){
        Formatter formatter = new Formatter();
        return formatter.format("%-30s", books).toString();
    }

    public static BookFormat getInstance() {
        if(instance == null){
            instance = new BookFormat();
        }
        return instance;
    }
}
