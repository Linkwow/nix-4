package ua.nix.bookslibrary.service.impl;

import ua.nix.bookslibrary.entity.Author;
import ua.nix.bookslibrary.entity.Book;
import ua.nix.bookslibrary.service.*;
import ua.nix.bookslibrary.service.filecreate.AuthorWrite;
import ua.nix.bookslibrary.service.fileread.AuthorRead;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private static final List<Author> authorList = new ArrayList<>();
    private static AuthorServiceImpl instance;

    private AuthorServiceImpl() {}

    @Override
    public void create(String name, String surname, String books) {
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        author.setBooks(books.split(","));
        authorList.add(author);
        AuthorWrite.getInstance().write(authorList);
    }

    @Override
    public void read(Integer id) {
        //createRelationship();
        printTable();
        List<String[]> authors = AuthorRead.getInstance().read();
        for (String[] row : authors) {
            if (row[0].contains(String.valueOf(id))) {
                System.out.println(Arrays.toString(authors.get(0)));
                System.out.println(Arrays.toString(row));
                break;
            }
        }
    }

    @Override
    public void update(Integer id, String name, String surname, String books) {
        printTable();
        for (Author author : authorList) {
            if (author.getId().equals(id)) {
                author.setName(name);
                author.setSurname(surname);
                author.setBooks(books.split(","));
                AuthorWrite.getInstance().write(authorList);
                break;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        printTable();
        for (Author author : authorList) {
            if (author.getId().equals(id)) {
                author.setVisible(false);
            }
        }
    }


    @Override
    public List<Class<? extends Book>> findAll() {
        return null;
    }

    @Override
    public void printTable() {
        List<String[]> authors = AuthorRead.getInstance().read();
        for (String[] array : authors) {
            System.out.println(Arrays.toString(array));
        }
    }

    private void createRelationships() {

    }

    public static AuthorServiceImpl getInstance() {
        if (instance == null) {
            instance = new AuthorServiceImpl();
        }
        return instance;
    }
}
