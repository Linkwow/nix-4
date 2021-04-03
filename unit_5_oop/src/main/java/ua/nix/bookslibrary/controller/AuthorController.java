package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.console.Console;
import ua.nix.bookslibrary.data.Author;
import ua.nix.bookslibrary.service.implementation.AuthorServiceImpl;

import java.util.Formatter;
import java.util.List;

public class AuthorController {
    private static AuthorController instance;
    private final Formatter formatter = new Formatter(System.out);

    public void create(){
        Author author = new Author();
        System.out.println("������� ��� ������.");
        author.setFirstName(Console.inputString());
        System.out.println("������� ������� ������.");
        author.setLastName(Console.inputString());
        System.out.println("������� �������� ����� ������ � ��� ������� �����, ���� ���� ���������, ��������� �� �������.");
        author.setBookList(Console.inputBookList());
        AuthorServiceImpl.getInstance().create(author);
    }

    public void printTable(){
        formatter.format("%-7s%-23s%-17s%-30s", "| ID ", "| AUTHOR_SURNAME", "| AUTHOR_NAME ", "| BOOKS");
        System.out.println();
        for (Author author : AuthorServiceImpl.getInstance().findAll()) {
            format(author.getId(), author.getLastName(), author.getFirstName(), author.getBooksList());
        }
        System.out.println();
    }

    public void read(){
        System.out.println("������� id ������.");
        Author author = AuthorServiceImpl.getInstance().read(Console.inputInt());
        formatter.format("%-7s%-23s%-17s%-30s", "| ID ", "| AUTHOR_SURNAME", "| AUTHOR_NAME ", "| BOOKS");
        System.out.println();
        format(author.getId(), author.getLastName(), author.getFirstName(), author.getBooksList());
    }

    public void readByTitle() {
        System.out.println("������� ������� ������.");
        String lastName = Console.inputString();
        System.out.println("������� ��� ������.");
        String firstName = Console.inputString();
        AuthorServiceImpl.getInstance().read(lastName, firstName);
    }

    public void update(){
        System.out.println("������� id ������ �������� ����� ��������.");
        int id = Console.inputInt();
        System.out.println("������� ��� ������.");
        String firstName = Console.inputString();
        System.out.println("������� ������� ������.");
        String lastName = Console.inputString();
        System.out.println("������� �������� ����� ������ � ��� ������� �����, ���� ���� ���������, ��������� �� �������.���");
        String[] books = Console.inputBookList();
        AuthorServiceImpl.getInstance().update(id, firstName, lastName, books);
    }

    public void delete(){
        System.out.println("������� id ������ �������� ����� �������.");
        int id = Console.inputInt();
        AuthorServiceImpl.getInstance().delete(id);
    }

    public void getAuthorsByBook(){
        System.out.println("������� id �����, ��� ������ ������� ����� ��������.");
        int id = Console.inputInt();
        for(Author a : AuthorServiceImpl.getInstance().getList(id)){
            System.out.println(a.getLastName() + " " +  a.getFirstName());
        }
    }

    private void format(int id, String lastName, String firstName, List<String> books){
        //Formatter formatter = new Formatter(System.out);
        formatter.format("%-7s%-23s%-17s%-30s", "| " + id + " ", "| " + lastName + " ", "| " + firstName, "| " + books);
        System.out.println();
    }

    public static AuthorController getInstance() {
        if(instance == null){
            instance = new AuthorController();
        }
        return instance;
    }
}