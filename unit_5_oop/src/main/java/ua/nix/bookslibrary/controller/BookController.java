package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.data.Book;
import ua.nix.bookslibrary.console.Console;
import ua.nix.bookslibrary.service.implementation.BookServiceImpl;

import java.util.Formatter;
import java.util.List;

public class BookController {
    private static BookController instance;
    private final Formatter formatter = new Formatter(System.out);

    public void create(){
        Book book = new Book();
        System.out.println("¬ведите название книги.");
        book.setTitle(Console.inputString());
        System.out.println("¬ведите год издани€ книги.");
        book.setYear(Console.inputString());
        System.out.println("¬ведите авторов книги, в формате им€ фамили€, если авторов несколько разделите их зап€той.");
        book.setAuthorList(Console.inputAuthorList());
        BookServiceImpl.getInstance().create(book);
    }

    public void printTable() {
        //Formatter formatter = new Formatter(System.out);
        formatter.format("%-7s%-25s%12s%-26s", "| ID ", "| BOOK_NAME ", "| BOOK_YEAR |", " AUTHORS");
        System.out.println();
        for (Book book : BookServiceImpl.getInstance().findAll()) {
           format(book.getId(), book.getTitle(), book.getYear(), book.getAuthorList());
        }
        System.out.println();
    }

    public void read(){
        System.out.println("¬ведите id книги.");
        Book book = BookServiceImpl.getInstance().read(Console.inputInt());
        //Formatter formatter = new Formatter(System.out);
        formatter.format("%-7s%-25s%12s%-26s", "| ID ", "| BOOK_NAME ", "| BOOK_YEAR |", " AUTHORS");
        System.out.println();
        format(book.getId(), book.getTitle(), book.getYear(), book.getAuthorList());
    }

    public void readByTitle() {
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
        System.out.println("¬ведите год издани€ книги.");
        String year = Console.inputString();
        System.out.println("\"¬ведите авторов книги, в формате фамили€ им€,если авторов несколько разделите их зап€той.");
        String[] authors = (Console.inputAuthorList());
        BookServiceImpl.getInstance().update(id, title, year, authors);
    }

    public void delete(){
        System.out.println("¬ведите id книги которую нужно удалить.");
        BookServiceImpl.getInstance().delete(Console.inputInt());
    }

    public void geBooksByAuthor(){
        System.out.println("¬ведите id автора, чей список книг нужно получить.");
        for(Book b : BookServiceImpl.getInstance().getList(Console.inputInt())){
           System.out.println(b.getTitle() + " " + b.getYear());
       }
    }

    private void format(int id, String title, String year, List<String> authors){
        formatter.format("%-7s%-25s%-12s%-27s", "| " + id + " ", "| " + title + " ", "| " + year, "| " + authors);
        System.out.println();
    }


    public static BookController getInstance() {
        if(instance == null){
            instance = new BookController();
        }
        return instance;
    }
}