package ua.nix.bookslibrary.service.implementation;

import ua.nix.bookslibrary.dao.implementation.BookDaoImpl;
import ua.nix.bookslibrary.data.Book;
import ua.nix.bookslibrary.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static BookServiceImpl instance;

    private BookServiceImpl(){}

    @Override
    public void create(Book book) {
        BookDaoImpl.getInstance().create(book);
    }

    @Override
    public void update(int id, String title, String year, String[] authors) {
        BookDaoImpl.getInstance().update(id, title, year, authors);
    }

    @Override
    public void delete(int id) {
        BookDaoImpl.getInstance().delete(id);
    }

    @Override
    public Book read(int id) {
       return BookDaoImpl.getInstance().read(id);
    }

    @Override
    public Book read(String title, String year){
       return BookDaoImpl.getInstance().read(title, year);
    }

    @Override
    public List<Book> findAll() {
        return BookDaoImpl.getInstance().findAll();
    }

    @Override
    public List<Book> getList(int id) {
        return BookDaoImpl.getInstance().getList(id);
    }

    public static BookServiceImpl getInstance() {
        if(instance == null){
            instance = new BookServiceImpl();
        }
        return instance;
    }
}