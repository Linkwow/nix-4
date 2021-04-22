package ua.nix.bookslibrary.service.impl;

import ua.nix.bookslibrary.entity.Author;
import ua.nix.bookslibrary.entity.Book;
import ua.nix.bookslibrary.service.BookService;
import ua.nix.bookslibrary.service.filecreate.BookWrite;
import ua.nix.bookslibrary.service.fileread.BookRead;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookServiceImpl implements BookService {
    private final List<Book> bookList = new ArrayList<>();
    private static BookServiceImpl instance;

    private BookServiceImpl(){}

    @Override
    public void create(String title, String authors) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(authors.split(","));
        bookList.add(book);
        BookWrite.getInstance().write(bookList);
    }

    @Override
    public void read(Integer id) {
        //createRelationship();
        printTable();
        List<String[]> books = BookRead.getInstance().read();
        for (String[] row : books) {
            if (row[0].contains(String.valueOf(id))) {
                System.out.println(Arrays.toString(books.get(0)));
                System.out.println(Arrays.toString(row));
                break;
            }
        }
    }

    @Override
    public void update(Integer id, String title, String authors) {
        printTable();
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setTitle(title);
                book.setAuthors(authors.split(","));
                BookWrite.getInstance().write(bookList);
                break;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        printTable();
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setVisible(false);
            }
        }
    }

    @Override
    public List<Class<? extends Author>> findAll() {
        return null;
    }

    @Override
    public void printTable() {
        List<String[]> books = BookRead.getInstance().read();
        for (String[] array : books) {
            System.out.println(Arrays.toString(array));
        }
    }

    private void createRelationships() {

    }

    public static BookServiceImpl getInstance() {
        if(instance == null){
            instance = new BookServiceImpl();
        }
        return instance;
    }
}
