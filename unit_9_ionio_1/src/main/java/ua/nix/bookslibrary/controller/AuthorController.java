package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.service.impl.AuthorServiceImpl;

import static ua.nix.libs.Console.inputInt;
import static ua.nix.libs.Console.inputString;

public class AuthorController implements BaseController {
    private static AuthorController instance;

    private AuthorController(){}

    @Override
    public void create() {
        System.out.println("Введите имя автора");
        String name = inputString();
        System.out.println("Введите фамилию автора");
        String surname = inputString();
        System.out.println("Введите список книг, если их несколько, разделите их запятой");
        String books = inputString();
        AuthorServiceImpl.getInstance().create(name, surname, books);
    }

    @Override
    public void read() {
        System.out.println("Введите идентификатор автора для вывода");
        Integer id = inputInt();
        AuthorServiceImpl.getInstance().read(id);
    }

    @Override
    public void update() {
        System.out.println("Введите идентификатор автора для обновления");
        Integer id = inputInt();
        System.out.println("Введите имя автора");
        String name = inputString();
        System.out.println("Введите фамилию автора");
        String surname = inputString();
        System.out.println("Введите список книг, если их несколько, разделите их запятой");
        String books = inputString();
        AuthorServiceImpl.getInstance().update(id, name, surname, books);
    }

    @Override
    public void delete() {
        System.out.println("Введите идентификатор автора для удаления");
        Integer id = inputInt();
        AuthorServiceImpl.getInstance().delete(id);
    }

    @Override
    public void findAll() {
       AuthorServiceImpl.getInstance().findAll();
    }

    @Override
    public void printTable() {
        AuthorServiceImpl.getInstance().printTable();
    }

    public static AuthorController getInstance() {
        if(instance == null){
            instance = new AuthorController();
        }
        return instance;
    }
}
