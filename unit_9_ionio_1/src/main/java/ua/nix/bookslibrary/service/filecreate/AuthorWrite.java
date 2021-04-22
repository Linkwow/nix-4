package ua.nix.bookslibrary.service.filecreate;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.nix.bookslibrary.entity.Author;
import ua.nix.bookslibrary.service.format.AuthorFormat;

public class AuthorWrite {
    private static AuthorWrite instance;
    private static final String file = "authors.csv";
    private final String[] header = new String[4];
    private List<String[]> authorList;

    private AuthorWrite(){}

    public void write(List<Author> authors) {
        authorList = new ArrayList<>();
        addHeader();
        Integer count = 1;
        for (Author author : authors) {
            if (author.isVisible()) {
                author.setId(count);
                addAuthor(author);
                count++;
            }
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))){
            writer.writeAll(authorList);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private void addHeader() {
        header[0] = AuthorFormat.getInstance().setIdFormat("id");
        header[1] = AuthorFormat.getInstance().setNameFormat("name");
        header[2] = AuthorFormat.getInstance().setSurNameFormat("surname");
        header[3] = AuthorFormat.getInstance().setBooksFormat("books");
        authorList.add(header);
    }

    private void addAuthor(Author author) {
        String[] authorData = new String[4];
        authorData[0] = AuthorFormat.getInstance().setIdFormat(author.getId().toString());
        authorData[1] = AuthorFormat.getInstance().setNameFormat(author.getName());
        authorData[2] = AuthorFormat.getInstance().setSurNameFormat(author.getSurname());
        authorData[3] = AuthorFormat.getInstance().setBooksFormat(author.getBooks().toString());
        authorList.add(authorData);
    }

    public static AuthorWrite getInstance() {
        if (instance == null) {
            instance = new AuthorWrite();
        }
        return instance;
    }
}