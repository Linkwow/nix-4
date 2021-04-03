package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.console.Console;
import ua.nix.bookslibrary.data.Author;
import ua.nix.bookslibrary.service.implementation.AuthorServiceImpl;

public class AuthorController {
    private static AuthorController instance;

    public void create(){
        Author author = new Author();
        System.out.println("¬ведите им€ автора.");
        author.setFirstName(Console.inputString());
        System.out.println("¬ведите фамилию автора.");
        author.setLastName(Console.inputString());
        System.out.println("¬ведите книги автора раздел€€ их зап€той.");
        author.setBookList(Console.inputList());
        AuthorServiceImpl.getInstance().create(author);
    }

    public void printTable(){
        System.out.println(AuthorServiceImpl.getInstance().findAll());
    }

    public void read(){
        System.out.println("¬ведите id автора.");
        System.out.println(AuthorServiceImpl.getInstance().read(Console.inputInt()));
    }

    public void readByTitle() throws ClassNotFoundException {
        System.out.println("¬ведите фамилию автора.");
        String lastName = Console.inputString();
        System.out.println("¬ведите им€ автора.");
        String firstName = Console.inputString();
        AuthorServiceImpl.getInstance().read(lastName, firstName);
    }

    public void update(){
        System.out.println("¬ведите id автора которого нужно изменить.");
        int id = Console.inputInt();
        System.out.println("¬ведите им€ автора.");
        String firstName = Console.inputString();
        System.out.println("¬ведите фамилию автора.");
        String lastName = Console.inputString();
        System.out.println("¬ведите книги автора раздел€€ их зап€той.");
        String[] books = Console.inputList();
        AuthorServiceImpl.getInstance().update(id, firstName, lastName, books);
    }

    public void delete(){
        System.out.println("¬ведите id автора которого нужно удалить.");
        int id = Console.inputInt();
        AuthorServiceImpl.getInstance().delete(id);
    }

    public void getAuthorList(){
        System.out.println("¬ведите id автора, чей список книг нужно получить.");
        int id = Console.inputInt();
        AuthorServiceImpl.getInstance().getBooksList(id);
    }

    public void getAuthorsByBook(){
        System.out.println("¬ведите id книги, чей список авторов нужно получить.");
        int id = Console.inputInt();
        for(Author a : AuthorServiceImpl.getInstance().getList(id)){
            System.out.println(a.getLastName() + a.getFirstName());
        }
    }

    public static AuthorController getInstance() {
        if(instance == null){
            instance = new AuthorController();
        }
        return instance;
    }
}
