package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.data.Book;
import ua.nix.bookslibrary.console.Console;
import ua.nix.bookslibrary.service.implementation.BookServiceImpl;

public class BookController {
    private static BookController instance;

    public void create(){
        Book book = new Book();
        System.out.println("������� �������� �����.");
        book.setTitle(Console.inputString());
        System.out.println("������� ��� ������� �����.");
        book.setYear(Console.inputString());
        System.out.println("������� ������� �����, �������� �� �������");
        book.setAuthorList(Console.inputList());
        BookServiceImpl.getInstance().create(book);
    }

    public void printTable(){
        System.out.println(BookServiceImpl.getInstance().findAll());
    }

    public void read(){
        System.out.println("������� id �����.");
        BookServiceImpl.getInstance().read(Console.inputInt());
    }

    public void readByTitle() throws ClassNotFoundException {
        System.out.println("������� �������� �����.");
        String title = Console.inputString();
        System.out.println("������� ��� �����.");
        String year = Console.inputString();
        BookServiceImpl.getInstance().read(title, year);
    }

    public void update(){
        System.out.println("������� id ����� ������� ����� ��������.");
        int id = Console.inputInt();
        System.out.println("������� �������� �����.");
        String title = Console.inputString();
        System.out.println("������� ������ ������� �����, �������� �� �������.");
        String[] authors = (Console.inputList());
        System.out.println("������� ��� ������� �����.");
        String year = Console.inputString();
        BookServiceImpl.getInstance().update(id, title, year, authors);
    }

    public void delete(){
        System.out.println("������� id ����� ������� ����� �������.");
        BookServiceImpl.getInstance().delete(Console.inputInt());
    }

    public void getAuthorList(){
        System.out.println("������� id �����, ��� ������ ������� ����� ��������.");
        BookServiceImpl.getInstance().getAuthorList(Console.inputInt());
    }

    public void geBooksByAuthor(){
        System.out.println("������� id ������, ��� ������ ���� ����� ��������.");
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