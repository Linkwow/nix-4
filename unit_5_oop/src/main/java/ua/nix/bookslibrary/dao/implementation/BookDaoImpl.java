package ua.nix.bookslibrary.dao.implementation;

import ua.nix.bookslibrary.dao.BookDao;
import ua.nix.bookslibrary.data.Book;
import ua.nix.bookslibrary.db.DataBase;

import java.util.List;

public class BookDaoImpl implements BookDao {
    private static BookDaoImpl instance;

    private BookDaoImpl() {
    }

    @Override
    public void create(Book book) {
        DataBase.getInstance().createBook(book);
    }

    @Override
    public Book read(int id) {
        return DataBase.getInstance().findBookById(id);
    }

    @Override
    public Book read(String title, String year) {
        return DataBase.getInstance().findBookByTitle(title, year);
    }

    @Override
    public void update(int id, String title, String year, String[] authors) {
        DataBase.getInstance().updateBook(id, title, year, authors);
    }

    @Override
    public void delete(int id) {
        DataBase.getInstance().deleteBook(id);
    }

    @Override
    public List<Book> findAll() {
        return DataBase.getInstance().findAllBooks();
    }

    @Override
    public void getAuthorList(int id) {
        DataBase.getInstance().getAuthorsList(id);
    }

    @Override
    public List<Book> getList(int id) {
        DataBase.getInstance().createRelationshipsBooksToAuthor();
        return DataBase.getInstance().getAllBooksByAuthor(id);
    }

    public static BookDaoImpl getInstance() {
        if (instance == null) {
            instance = new BookDaoImpl();
        }
        return instance;
    }
}