package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.console.Console;
import ua.nix.bookslibrary.data.Author;
import ua.nix.bookslibrary.service.implementation.AuthorServiceImpl;

public class AuthorController {
    private static AuthorController instance;

    public void create(){
        Author author = new Author();
        System.out.println("������� ��� ������.");
        author.setFirstName(Console.inputString());
        System.out.println("������� ������� ������.");
        author.setLastName(Console.inputString());
        System.out.println("������� ����� ������ �������� �� �������.");
        author.setBookList(Console.inputList());
        AuthorServiceImpl.getInstance().create(author);
    }

    public void printTable(){
        System.out.println(AuthorServiceImpl.getInstance().findAll());
    }

    public void read(){
        System.out.println("������� id ������.");
        System.out.println(AuthorServiceImpl.getInstance().read(Console.inputInt()));
    }

    public void readByTitle() throws ClassNotFoundException {
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
        System.out.println("������� ����� ������ �������� �� �������.");
        String[] books = Console.inputList();
        AuthorServiceImpl.getInstance().update(id, firstName, lastName, books);
    }

    public void delete(){
        System.out.println("������� id ������ �������� ����� �������.");
        int id = Console.inputInt();
        AuthorServiceImpl.getInstance().delete(id);
    }

    public void getAuthorList(){
        System.out.println("������� id ������, ��� ������ ���� ����� ��������.");
        int id = Console.inputInt();
        AuthorServiceImpl.getInstance().getBooksList(id);
    }

    public void getAuthorsByBook(){
        System.out.println("������� id �����, ��� ������ ������� ����� ��������.");
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
