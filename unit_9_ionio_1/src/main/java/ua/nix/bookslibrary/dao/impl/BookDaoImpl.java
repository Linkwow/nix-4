package ua.nix.bookslibrary.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvException;
import ua.nix.bookslibrary.dao.BookDao;
import ua.nix.bookslibrary.entity.Book;
import ua.nix.bookslibrary.dao.impl.format.BookFormat;

import org.apache.log4j.Logger;

public class BookDaoImpl implements BookDao {
    private final Logger logger = Logger.getLogger(BookDaoImpl.class);
    private static BookDaoImpl instance;
    private final String file = "src\\main\\java\\ua\\nix\\bookslibrary\\database\\books.csv";
    private final String[] header = new String[5];
    private List<String[]> headerList = new ArrayList<>();
    private List<String[]> data;
    private boolean needHeader = true;
    private Integer id = 1;

    private BookDaoImpl() {
    }

    @Override
    public void create(Book book) {
        logger.info("Method is starting to create book");
        createHeader();
        book.setId(id++);
        addBook(book);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {
            writer.writeAll(data);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
        logger.info("Method is finishing to create book");
    }

    @Override
    public Book read(Integer id) {
        logger.info("Method is starting to read book");
        String[] outData;
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            int rowCounter = 0;
            while ((outData = reader.readNext()) != null) {
                if (rowCounter != 0) {
                    if (Integer.parseInt(outData[0].replaceAll("\\s*", "")) == id) {
                        return createBookFromString(outData);
                    }
                }
                rowCounter++;
            }
        } catch (CsvException csvException) {
            System.err.println(csvException.getMessage());
        } catch (IOException exception) {
            System.err.println("Ошибка чтения объекта");
        } finally{
            logger.info("Method is finishing to read book");
        }
        throw new IndexOutOfBoundsException("Вы ввели идентификатор который отсутствуетв базе, проверьте правильнсть идентификатора");
    }

    @Override
    public List<String[]> readAll() {
        logger.info("Method is starting to read all books");
        List<String[]> allFile = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            allFile = reader.readAll();
        } catch (CsvException csvException) {
            System.err.println(csvException.getMessage());
        } catch (IOException exception) {
            System.err.println("Ошибка чтения файла");
        } finally {
            logger.info("Method is finishing to read all books");
        }

        return allFile;
    }

    @Override
    public void update(Integer id, String title, String authors) {
        logger.info("Method is starting to update book");
        Book bookToUpdate = createBookByParameters(id, title, authors);
        update(bookToUpdate);
        logger.info("Method is finishing to update book");
    }

    @Override
    public void delete(Integer id) {
        logger.info("Method is starting to delete book");
        Book bookToDelete = read(id);
        bookToDelete.setVisible(false);
        update(bookToDelete);
        logger.info("Method is finishing to delete book");
    }

    @Override
    public List<String> findAll(Integer id) {
        logger.info("Method is starting to find all books");
        return read(id).getAuthors();
    }

    @Override
    public List<Book> takeAll(List<String> entities) {
        List<Book> bookByAuthor = new ArrayList<>();
        List<String[]> outData = readAll();
        for (String[] bookTitleInBase : outData) {
            for(String title : entities)
            if (bookTitleInBase[1].contains(title)){
                bookByAuthor.add(read(Integer.parseInt(bookTitleInBase[0].replaceAll("\\s*", ""))));
            }
        }
        logger.info("Method is finishing to find all books");
        return bookByAuthor;
    }

    private void createHeader() {
        if (needHeader) {
            addHeader();
            try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
                writer.writeAll(headerList);
                needHeader = false;
            } catch (IOException exception) {
                System.err.println("Ошибка создания заголовка");
            }
        }
    }

    private Book createBookFromString(String[] outData) {
        Book book = new Book();
        book.setId(Integer.parseInt(outData[0].replaceAll("\\s*", "")));
        book.setTitle(outData[1].replaceAll("\\s{2,}", ""));
        book.setAuthors(outData[2].replaceAll("[\\[\\]]", "").
                replaceAll("\\s{2,}", "")
                .split(",\\s?"));
        book.setVisible(Boolean.parseBoolean(outData[3].replaceAll("\\s*", "")));
        return book;
    }

    private Book createBookByParameters(Integer id, String title, String authors) {
        Book bookToUpdate = read(id);
        bookToUpdate.setTitle(title);
        bookToUpdate.setAuthors(authors.split(",\\s?"));
        return bookToUpdate;
    }

    private void update(Book book) {
        Book bookFromTable;
        data = readAll();
        needHeader = true;
        this.id = 1;
        int rowCounter = 0;
        for (String[] array : data) {
            if (rowCounter != 0) {
                bookFromTable = createBookFromString(array);
                if (bookFromTable.getId().equals(book.getId())) {
                    create(book);
                } else {
                    create(bookFromTable);
                }
            }
            rowCounter++;
        }
        needHeader = false;
    }

    private void addHeader() {
        headerList = new ArrayList<>();
        header[0] = BookFormat.getInstance().setIdFormat("id");
        header[1] = BookFormat.getInstance().setTitleFormat("title");
        header[2] = BookFormat.getInstance().setAuthorsFormat("authors");
        header[3] = BookFormat.getInstance().setVisibleFormat("visible");
        headerList.add(header);
    }

    private void addBook(Book book) {
        String[] bookData = new String[4];
        bookData[0] = BookFormat.getInstance().setIdFormat(book.getId().toString());
        bookData[1] = BookFormat.getInstance().setTitleFormat(book.getTitle());
        bookData[2] = BookFormat.getInstance().setAuthorsFormat(book.getAuthors().toString());
        bookData[3] = BookFormat.getInstance().setVisibleFormat(book.isVisible());
        data = new ArrayList<>();
        data.add(bookData);
    }

    public static BookDaoImpl getInstance() {
        if (instance == null) {
            instance = new BookDaoImpl();
        }
        return instance;
    }
}