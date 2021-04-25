package ua.nix.bookslibrary.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvException;
import ua.nix.bookslibrary.dao.AuthorDao;
import ua.nix.bookslibrary.entity.Author;
import ua.nix.bookslibrary.dao.impl.format.AuthorFormat;

import org.apache.log4j.Logger;

public class AuthorDaoImpl implements AuthorDao {
    private final Logger logger = Logger.getLogger(AuthorDaoImpl.class);
    private static AuthorDaoImpl instance;
    private final String file = "src\\main\\java\\ua\\nix\\bookslibrary\\database\\authors.csv";
    private final String[] header = new String[5];
    private List<String[]> headerList = new ArrayList<>();
    private List<String[]> data;
    private boolean needHeader = true;
    private Integer id = 1;


    private AuthorDaoImpl() {
    }

    @Override
    public void create(Author author) {
        logger.info("Method is starting to create author");
        createHeader();
        author.setId(id++);
        addAuthor(author);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {
            writer.writeAll(data);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
        logger.info("Method is finishing creating author");
    }

    @Override
    public Author read(Integer id) {
        logger.info("Method is starting to read author");
        String[] outData;
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            int rowCounter = 0;
            while ((outData = reader.readNext()) != null) {
                if (rowCounter != 0) {
                    if (Integer.parseInt(outData[0].replaceAll("\\s*", "")) == id) {
                        return createAuthorFromString(outData);
                    }
                }
                rowCounter++;
            }
        } catch (CsvException csvException) {
            System.err.println(csvException.getMessage());
        } catch (IOException exception) {
            System.err.println("Ошибка чтения объекта");
        } finally {
            logger.info("Method is finishing to read author");
        }
        throw new IndexOutOfBoundsException("Вы ввели идентификатор который отсутствуетв базе, проверьте правильнсть идентификатора");

    }

    @Override
    public List<String[]> readAll() {
        logger.info("Method is starting to read all authors");
        List<String[]> allFile = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            allFile = reader.readAll();
        } catch (CsvException csvException) {
            System.err.println(csvException.getMessage());
        } catch (IOException exception) {
            System.err.println("Ошибка чтения файла");
        } finally {
            logger.info("Method is finishing to read all authors");
        }
        return allFile;
    }

    @Override
    public void update(Integer id, String name, String surname, String books) {
        logger.info("Method is starting to update author");
        Author authorToUpdate = createAuthorByParameters(id, name, surname, books);
        update(authorToUpdate);
        logger.info("Method is finishing to update author");
    }

    @Override
    public void delete(Integer id) {
        logger.info("Method is starting to delete author");
        Author authorToDelete = read(id);
        authorToDelete.setVisible(false);
        update(authorToDelete);
        logger.info("Method is finishing to delete author");
    }

    @Override
    public List<String> findAll(Integer id) {
        logger.info("Method is starting to find all author");
        return read(id).getBooks();
    }

    @Override
    public List<Author> takeAll(List<String> entities) {
        String[] authorName;
        List<Author> authorByBook = new ArrayList<>();
        List<String[]> outData = readAll();
        for (String[] authorInitialsInBase : outData) {
            for (String initials : entities) {
                authorName = initials.split(" ");
                if (authorInitialsInBase[1].replaceAll("\\s{2,}", "")
                        .equals(authorName[0]) && authorInitialsInBase[2].
                        replaceAll("\\s{2,}", "").equals(authorName[1])) {
                    authorByBook.add(read(Integer.parseInt(authorInitialsInBase[0].replaceAll("\\s*", ""))));
                }
            }
        }
        logger.info("Method is finishing to find all author");
        return authorByBook;
    }

    private void createHeader() {
        if (needHeader) {
            addHeader();
            try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
                writer.writeAll(headerList);
                needHeader = false;
            } catch (IOException exception) {
                System.err.println(exception.getMessage());
            }
        }
    }

    private Author createAuthorFromString(String[] outData) {
        Author author = new Author();
        author.setId(Integer.parseInt(outData[0].replaceAll("\\s*", "")));
        author.setName(outData[1].replaceAll("\\s{2,}", ""));
        author.setSurname(outData[2].replaceAll("\\s{2,}", ""));
        author.setBooks(outData[3].replaceAll("[\\[\\]]", "").
                replaceAll("\\s{2,}", "")
                .split(",\\s?"));
        author.setVisible(Boolean.parseBoolean(outData[4].replaceAll("\\s*", "")));
        return author;
    }

    private void addHeader() {
        headerList = new ArrayList<>();
        header[0] = AuthorFormat.getInstance().setIdFormat("id");
        header[1] = AuthorFormat.getInstance().setNameFormat("name");
        header[2] = AuthorFormat.getInstance().setSurNameFormat("surname");
        header[3] = AuthorFormat.getInstance().setBooksFormat("books");
        header[4] = AuthorFormat.getInstance().setVisibleFormat("visible");
        headerList.add(header);
    }

    private void addAuthor(Author author) {
        String[] authorData = new String[5];
        authorData[0] = AuthorFormat.getInstance().setIdFormat(author.getId().toString());
        authorData[1] = AuthorFormat.getInstance().setNameFormat(author.getName());
        authorData[2] = AuthorFormat.getInstance().setSurNameFormat(author.getSurname());
        authorData[3] = AuthorFormat.getInstance().setBooksFormat(author.getBooks().toString());
        authorData[4] = AuthorFormat.getInstance().setVisibleFormat(author.isVisible());
        data = new ArrayList<>();
        data.add(authorData);
    }

    private Author createAuthorByParameters(Integer id, String name, String surname, String books) {
        Author authorToUpdate = read(id);
        authorToUpdate.setName(name);
        authorToUpdate.setSurname(surname);
        authorToUpdate.setBooks(books.split(",\\s?"));
        return authorToUpdate;
    }

    private void update(Author author) {
        Author authorFromTable;
        data = readAll();
        needHeader = true;
        this.id = 1;
        int rowCounter = 0;
        for (String[] array : data) {
            if (rowCounter != 0) {
                authorFromTable = createAuthorFromString(array);
                if (authorFromTable.getId().equals(author.getId())) {
                    create(author);
                } else {
                    create(authorFromTable);
                }
            }
            rowCounter++;
        }
        needHeader = false;
    }

    public static AuthorDaoImpl getInstance() {
        if (instance == null) {
            instance = new AuthorDaoImpl();
        }
        return instance;
    }
}