package ua.nix.bookslibrary.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.nix.bookslibrary.entity.Author;
import ua.nix.bookslibrary.entity.Book;
import ua.nix.bookslibrary.service.AuthorService;
import ua.nix.bookslibrary.service.filecreate.AuthorWrite;
import ua.nix.bookslibrary.service.fileread.AuthorRead;


public class AuthorServiceImpl implements AuthorService<Author> {
    private static final List<Author> authorList = new ArrayList<>();
    private static AuthorServiceImpl instance;

    private AuthorServiceImpl() {
    }

    @Override
    public void create(String name, String surname, String books) {
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        author.setBooks(prepareBooksToSet(books.split(",")));
        authorList.add(author);
        AuthorWrite.getInstance().write(authorList);
    }

    @Override
    public void read(Integer id) {
        List<String[]> authors = AuthorRead.getInstance().read();
        for (String[] row : authors) {
            if (row[0].contains(String.valueOf(id))) {
                System.out.println(Arrays.toString(authors.get(0)));
                System.out.println(Arrays.toString(row));
                break;
            }
        }
    }

    @Override
    public void update(Integer id, String name, String surname, String books) {
        printTable();
        for (Author author : authorList) {
            if (author.getId().equals(id)) {
                author.setName(name);
                author.setSurname(surname);
                author.setBooks(prepareBooksToSet(books.split(",")));
                AuthorWrite.getInstance().write(authorList);
                break;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        printTable();
        for (Author author : authorList) {
            if (author.getId().equals(id)) {
                author.setVisible(false);
                AuthorWrite.getInstance().write(authorList);
                break;
            }
        }
    }

    @Override
    public void findAll(Integer id) {
        createRelationships();
        for(Author author : authorList){
            if(author.getId().equals(id)){
               for(Class<? extends Book> book : author.getBookList()){
                   try {
                       System.out.println(book.getDeclaredConstructor().newInstance());
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
        List<String[]> authors = AuthorRead.getInstance().read();
        for (String[] array : authors) {
            System.out.println(Arrays.toString(array));
        }
    }

    @Override
    public List<Author> getAll() {
        return authorList;
    }

    private void createRelationships() {
        String[] authorInitials;
        for (Author author : authorList) {
            for (Book book : BookServiceImpl.getInstance().getAll()) {
                for (String initials : book.getAuthors()) {
                    authorInitials = initials.split(" ");
                    if (authorInitials[0].equals(author.getName()) && authorInitials[1].equals(author.getSurname())) {
                        author.setBookList(book);
                    }
                }
            }
        }
    }

    private String[] prepareBooksToSet(String[] books){
        String[] temp = new String[books.length];
        for (int index = 0; index < books.length; index++) {
            if (books[index].charAt(0) == ' ') {
                temp[index] = books[index].substring(1);
            } else {
                temp[index] = books[index];
            }
        }
        return temp;
    }

    public static AuthorServiceImpl getInstance() {
        if (instance == null) {
            instance = new AuthorServiceImpl();
        }
        return instance;
    }
}