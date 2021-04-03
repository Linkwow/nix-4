package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.data.Book;
import ua.nix.bookslibrary.console.Console;
import ua.nix.bookslibrary.service.implementation.BookServiceImpl;

public class BookController {
    private static BookController instance;

    public void create(){
        Book book = new Book();
        System.out.println("¬ведите название книги.");
        book.setTitle(Console.inputString());
        System.out.println("¬ведите год издани€ книги.");
        book.setYear(Console.inputString());
        System.out.println("¬ведите авторов книги, раздел€€ их зап€той");
        book.setAuthorList(Console.inputList());
        BookServiceImpl.getInstance().create(book);
    }

    public void printTable(){
        System.out.println(BookServiceImpl.getInstance().findAll());
    }

    public void read(){
        System.out.println("¬ведите id книги.");
        BookServiceImpl.getInstance().read(Console.inputInt());
    }

    public void readByTitle() throws ClassNotFoundException {
        System.out.println("¬ведите название книги.");
        String title = Console.inputString();
        System.out.println("¬ведите год книги.");
        String year = Console.inputString();
        BookServiceImpl.getInstance().read(title, year);
    }

    public void update(){
        System.out.println("¬ведите id книги которую нужно изменить.");
        int id = Console.inputInt();
        System.out.println("¬ведите название книги.");
        String title = Console.inputString();
        System.out.println("¬ведите список авторов книги, раздел€€ их зап€той.");
        String[] authors = (Console.inputList());
        System.out.println("¬ведите год издани€ книги.");
        String year = Console.inputString();
        BookServiceImpl.getInstance().update(id, title, year, authors);
    }

    public void delete(){
        System.out.println("¬ведите id книги которую нужно удалить.");
        BookServiceImpl.getInstance().delete(Console.inputInt());
    }

    public void getAuthorList(){
        System.out.println("¬ведите id книги, чей список авторов нужно получить.");
        BookServiceImpl.getInstance().getAuthorList(Console.inputInt());
    }

    public void geBooksByAuthor(){
        System.out.println("¬ведите id автора, чей список книг нужно получить.");
        for(Book b : BookServiceImpl.getInstance().getList(Console.inputInt())){
           System.out.println(b.getTitle() + b.getYear());
       }
    }



    public static BookController getInstance() {
        if(instance == null){
            instance = new BookController();
        }
        return instance;
    }
}