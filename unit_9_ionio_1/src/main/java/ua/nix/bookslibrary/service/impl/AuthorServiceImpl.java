package ua.nix.bookslibrary.service.impl;

import java.util.Arrays;
import java.util.List;

import ua.nix.bookslibrary.dao.impl.BookDaoImpl;
import ua.nix.bookslibrary.entity.Author;
import ua.nix.bookslibrary.service.AuthorService;
import ua.nix.bookslibrary.dao.impl.AuthorDaoImpl;

public class AuthorServiceImpl implements AuthorService {
    private static AuthorServiceImpl instance;

    private AuthorServiceImpl() {}

    @Override
    public void create(String name, String surname, String books) {
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        author.setBooks(books.split(",\\s?"));
        AuthorDaoImpl.getInstance().create(author);
    }

    @Override
    public void read(Integer id) {
        try {
            Author author = AuthorDaoImpl.getInstance().read(id);
            System.out.println(author);
        } catch (NumberFormatException numberFormatException){
            System.err.println(numberFormatException.getMessage());
        }
    }

    @Override
    public void update(Integer id, String name, String surname, String books) {
        AuthorDaoImpl.getInstance().update(id, name, surname, books);
    }

    @Override
    public void delete(Integer id) {
        AuthorDaoImpl.getInstance().delete(id);
    }

    @Override
    public void findAll(Integer id) {
        List<String> books = AuthorDaoImpl.getInstance().findAll(id);
        System.out.println((BookDaoImpl.getInstance().takeAll(books).toString()));
    }

    @Override
    public void printTable() {
        List<String[]> authors = AuthorDaoImpl.getInstance().readAll();
        for (String[] array : authors) {
            System.out.println(Arrays.toString(array));
        }
        System.out.println();
    }

    public static AuthorServiceImpl getInstance() {
        if (instance == null) {
            instance = new AuthorServiceImpl();
        }
        return instance;
    }
}