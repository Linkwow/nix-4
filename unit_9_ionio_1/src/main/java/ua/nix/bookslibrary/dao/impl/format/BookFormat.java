package ua.nix.bookslibrary.dao.impl.format;

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
        return formatter.format("%-25s", name).toString();
    }

    public String setAuthorsFormat(String books){
        Formatter formatter = new Formatter();
        return formatter.format("%-40s", books).toString();
    }

    public String setVisibleFormat(String visible){
        Formatter formatter = new Formatter();
        return formatter.format("%-8s", visible).toString();
    }


    public static BookFormat getInstance() {
        if(instance == null){
            instance = new BookFormat();
        }
        return instance;
    }
}
