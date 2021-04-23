package ua.nix.bookslibrary.service.impl;

import ua.nix.bookslibrary.entity.Author;
import ua.nix.bookslibrary.entity.Book;
import ua.nix.bookslibrary.service.BookService;
import ua.nix.bookslibrary.service.filecreate.BookWrite;
import ua.nix.bookslibrary.service.fileread.BookRead;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookServiceImpl implements BookService<Book> {
    private final List<Book> bookList = new ArrayList<>();
    private static BookServiceImpl instance;

    private BookServiceImpl(){}

    @Override
    public void create(String title, String authors) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(prepareAuthorToSet(authors.split(",")));
        bookList.add(book);
        BookWrite.getInstance().write(bookList);
    }

    @Override
    public void read(Integer id) {
        //createRelationship();
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
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setTitle(title);
                book.setAuthors(prepareAuthorToSet(authors.split(",")));
                BookWrite.getInstance().write(bookList);
                break;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setVisible(false);
                BookWrite.getInstance().write(bookList);
                break;
            }
        }
    }

    @Override
    public void findAll(Integer id) {
        createRelationships();
        for(Book book : bookList){
            if(book.getId().equals(id)){
                for(Class<? extends Author> author : book.getAuthorList()){
                    try {
                        Author authorTest = author.getDeclaredConstructor().newInstance();
                        System.out.println(authorTest);
                    } catch (NoSuchMethodException noSuchMethodException){
                        System.err.println("У объекта нет конструктора");
                    } catch (InstantiationException instantiationException){
                        System.err.println("Невозможно создать экземпляр объекта, проверьте конструктор");
                    } catch (IllegalAccessException illegalAccessException){
                        System.err.println("проверьте модификатор доступа");
                    } catch (InvocationTargetException invocationTargetException){
                        System.err.println("Невозможно создать экземпляр объекта");
                    }
                }
            }
        }
    }

    @Override
    public void printTable() {
        List<String[]> books = BookRead.getInstance().read();
        for (String[] array : books) {
            System.out.println(Arrays.toString(array));
        }
    }

    @Override
    public List<Book> getAll() {
        return bookList;
    }

    private void createRelationships() {
        for (Book book : bookList) {
            for (Author author : AuthorServiceImpl.getInstance().getAll()) {
                for (String titles : author.getBooks()) {
                    if (titles.equals(book.getTitle())) {
                        book.setAuthorList(author);
                    }
                }
            }
        }
    }

    private String[] prepareAuthorToSet(String[] authors){
        String[] temp = new String[authors.length];
        for (int index = 0; index < authors.length; index++) {
            if (authors[index].charAt(0) == ' ') {
                temp[index] = authors[index].substring(1);
            } else {
                temp[index] = authors[index];
            }
        }
        return temp;
    }

    public static BookServiceImpl getInstance() {
        if(instance == null){
            instance = new BookServiceImpl();
        }
        return instance;
    }
}
