package ua.nix.bookslibrary.service.filecreate;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ua.nix.bookslibrary.entity.Book;
import ua.nix.bookslibrary.service.format.BookFormat;


public class BookWrite {

    private static BookWrite instance;
    private static final String file = "books.csv";
    private final String[] header = new String[3];
    private List<String[]> bookList;

    private BookWrite() {
    }

    public void write(List<Book> authors) {
        bookList = new ArrayList<>();
        addHeader();
        Integer count = 1;
        for (Book book : authors) {
            if (book.isVisible()) {
                book.setId(count);
                addBook(book);
                count++;
            }
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(bookList);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private void addHeader() {
        header[0] = BookFormat.getInstance().setIdFormat("id");
        header[1] = BookFormat.getInstance().setTitleFormat("title");
        header[2] = BookFormat.getInstance().setAuthorsFormat("authors");
        bookList.add(header);
    }

    private void addBook(Book book) {
        String[] authorData = new String[3];
        authorData[0] = BookFormat.getInstance().setIdFormat(book.getId().toString());
        authorData[1] = BookFormat.getInstance().setTitleFormat(book.getTitle());
        authorData[2] = BookFormat.getInstance().setAuthorsFormat(book.getAuthors().toString());
        bookList.add(authorData);
    }

    public static BookWrite getInstance() {
        if (instance == null) {
            instance = new BookWrite();
        }
        return instance;
    }
}
