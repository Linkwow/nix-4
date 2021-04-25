package ua.nix.bookslibrary.service.impl;

import ua.nix.bookslibrary.dao.impl.AuthorDaoImpl;
import ua.nix.bookslibrary.entity.Book;
import ua.nix.bookslibrary.service.BookService;
import ua.nix.bookslibrary.dao.impl.BookDaoImpl;

import java.util.Arrays;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static BookServiceImpl instance;

    private BookServiceImpl() {
    }

    @Override
    public void create(String title, String authors) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(authors.split(",\\s?"));
        BookDaoImpl.getInstance().create(book);
    }

    @Override
    public void read(Integer id) {
        try {
            Book book = BookDaoImpl.getInstance().read(id);
            System.out.println(book);
        } catch (NumberFormatException numberFormatException) {
            System.err.println(numberFormatException.getMessage());
        }
    }

    @Override
    public void update(Integer id, String title, String authors) {
        BookDaoImpl.getInstance().update(id, title, authors);
    }

    @Override
    public void delete(Integer id) {
        BookDaoImpl.getInstance().delete(id);
    }

    @Override
    public void findAll(Integer id) {
        List<String> authors = BookDaoImpl.getInstance().findAll(id);
        System.out.println(AuthorDaoImpl.getInstance().takeAll(authors).toString());
    }

    @Override
    public void printTable() {
        List<String[]> books = BookDaoImpl.getInstance().readAll();
        for (String[] array : books) {
            System.out.println(Arrays.toString(array));
        }
        System.out.println();
    }

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }
}
