package ua.nix.bookslibrary.dao.impl.format;

import java.util.Formatter;

public class AuthorFormat {
    private static AuthorFormat instance;

    private AuthorFormat(){}

    public String setIdFormat(String id){
        Formatter formatter = new Formatter();
        return formatter.format("%-5s", id).toString();
    }

    public String setNameFormat(String name){
        Formatter formatter = new Formatter();
        return formatter.format("%-15s", name).toString();
    }

    public String setSurNameFormat(String surname){
        Formatter formatter = new Formatter();
        return formatter.format("%-25s", surname).toString();
    }

    public String setBooksFormat(String books){
        Formatter formatter = new Formatter();
        return formatter.format("%-30s", books).toString();
    }

    public String setVisibleFormat(String visible){
        Formatter formatter = new Formatter();
        return formatter.format("%-8s", visible).toString();
    }

    public static AuthorFormat getInstance() {
       if(instance == null){
            instance = new AuthorFormat();
        }
        return instance;
    }
}
