package ua.nix.bookslibrary.db;

import ua.nix.bookslibrary.controller.AuthorController;
import ua.nix.bookslibrary.data.*;

import java.util.*;

import org.apache.log4j.Logger;

public class DataBase {
    private final Logger logger = Logger.getLogger(AuthorController.class);
    private static DataBase instance;
    private final List<Author> authorList = new ArrayList<>();
    private final List<Book> bookList = new ArrayList<>();
    private final String[] nullArray = new String[]{"null"};
    private int bookId;
    private int authorId;

    private DataBase() {}

    public void createBook(Book book) {
        if (book.getAuthorList().get(0).isBlank()) {
            logger.info("Method creating book with null author starts");
            System.out.println("Вы не ввели авторов, полю со списком авторов будет установлено значение null");
            book.setAuthorList(nullArray);
            book.setId(bookId++);
            bookList.add(book);
            logger.info("Finish create book with null author");
        } else {
            logger.info("Start to create book with author");
            bookList.add(book);
            book.setId(bookId++);
            logger.info("Finish create book with author");
        }
    }

    public void createAuthor(Author author) {
        if (author.getBooksList().get(0).isBlank()) {
            logger.info("Method creating author with null book starts");
            System.out.println("Вы не ввели книги, полю со списком книг будет установлено значение null");
            author.setBookList(nullArray);
            author.setId(authorId++);
            authorList.add(author);
            logger.info("Method creating author with null book ends");
        } else {
            logger.info("Method creating author with book starts");
            authorList.add(author);
            author.setId(authorId++);
            logger.info("Method creating author with book ends");
        }
    }

    public List<Book> findAllBooks() {
        return bookList;
    }

    public List<Author> findAllAuthors() {
        return authorList;
    }

    public Book findBookById(int id) {
        logger.info("Read book by id starts");
        try {
            for (Book book : bookList) {
                if (book.getId() == id) {
                    logger.info("Return book by id");
                    return book;
                }
            }
            throw new ClassNotFoundException("В базе отстутствует введенный идентификатор");
        } catch (ClassNotFoundException c) {
            System.err.println(c.getMessage());
            throw new RuntimeException();
        }
    }

    public Author findAuthorById(int id) {
        logger.info("Read author by id starts");
        try {
            for (Author author : authorList) {
                if (author.getId() == id) {
                    logger.info("Return author by id");
                    return author;
                }
            }
            throw new ClassNotFoundException("В базе отстутствует введенный идентификатор");
        } catch (ClassNotFoundException c) {
            System.err.println(c.getMessage());
            throw new RuntimeException();
        }
    }

    public Book findBookByTitle(String bookTitle, String bookYear) {
        logger.info("Start find book by title");
        try {
            for (Book book : bookList) {
                if (book.getTitle().equals(bookTitle) && book.getYear().equals(bookYear)) {
                    logger.info("Find book by title");
                    return book;
                }
            }
            throw new ClassNotFoundException("В базе отстутствует введенная книга");
        } catch (ClassNotFoundException c) {
            System.err.println(c.getMessage());
            throw new RuntimeException();
        }
    }

    public Author findAuthorByInitials(String lastName, String firstName) {
        logger.info("Start find author by title");
        try {
            for (Author author : authorList) {
                if (author.getLastName().equals(lastName) && author.getFirstName().equals(firstName)) {
                    logger.info("Find author by title");
                    return author;
                }
            }
            throw new ClassNotFoundException("В базе отстутствует введенный автор");
        } catch (ClassNotFoundException c) {
            System.err.println(c.getMessage());
            throw new RuntimeException();
        }
    }

    public void updateBook(int id, String title, String year, String[] authors) {
        logger.info("Start update book");
        Book book = bookList.get(id);
        book.setTitle(title);
        book.setYear(year);
        book.clear();
        book.setAuthorList(authors);
        logger.info("End update book");
    }

    public void updateAuthor(int id, String firstName, String lastName, String[] authors) {
        logger.info("Start update author");
        Author author = authorList.get(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.clear();
        author.setBookList(authors);
        logger.info("End update author");
    }

    public void deleteBook(int id) {
        logger.info("Start delete book");
        List<Book> tempList = new ArrayList<>();
        tempList.containsAll(bookList);
        for (Book book : tempList) {
            if(book.getId() == id)
                tempList.remove(bookList.indexOf(book));
        }
        bookList.clear();
        bookList.containsAll(tempList);
        logger.info("End delete book");
    }

    public void deleteAuthor(int id) {
        logger.info("Start delete author");
        List<Author> tempList = new ArrayList<>();
        tempList.containsAll(authorList);
        for (Author author : tempList) {
            if(author.getId() == id)
                tempList.remove(authorList.indexOf(author));
        }
        authorList.clear();
        authorList.containsAll(tempList);
        logger.info("End delete author");
    }

    public void getAuthorsList(int id) {
        System.out.println(bookList.get(id).getAuthorList());
    }

    public void getBookList(int id) {
        System.out.println(authorList.get(id).getBooksList());
    }

    public void createRelationshipsAuthorsToBook() {
        logger.info("Start create relationships");
        for (Book book : findAllBooks()) {
            authorToBook(book);
        }
    }

    public void createRelationshipsBooksToAuthor() {
        for (Author author : findAllAuthors()) {
            bookToAuthor(author);
        }
        logger.info("End create relationships");
    }

    private void authorToBook(Book book) {
        for (String author : book.getAuthorList()) {
            if (!author.equals("null")) {
                String[] initials = author.split(" ");
                String firstName = initials[0];
                String lastName = initials[1];
                book.setAuthorId(findAuthorByInitials(lastName, firstName).getId());
            }
        }
    }

    private void bookToAuthor(Author author) {
        for (String book : author.getBooksList()) {
            if (!book.equals("null")) {
                String[] info = book.split(" ");
                String title = info[0];
                String year = info[1];
                int id = findBookByTitle(title, year).getId();
                author.setBookId(id);
            }
        }
    }

    public List<Author> getAllAuthorsByBook(int id){
        List<Author> allAuthors = new ArrayList<>();
        for (int i : findBookById(id).getAuthorId()){
            allAuthors.add(findAuthorById(i));
        }
        return allAuthors;
    }

    public List<Book> getAllBooksByAuthor(int id){
        List<Integer> booksIdList = findAuthorById(id).getBookId();
        List<Book> allBooks = new ArrayList<>();
        for (int i : booksIdList){
            allBooks.add(findBookById(i));
        }
        return allBooks;
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }
}