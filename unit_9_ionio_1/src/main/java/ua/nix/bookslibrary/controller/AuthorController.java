package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.service.impl.AuthorServiceImpl;

import java.util.InputMismatchException;

import static ua.nix.libs.Console.inputInt;
import static ua.nix.libs.Console.inputString;

public class AuthorController implements BaseController {
    private static AuthorController instance;

    private AuthorController() {
    }

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
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            AuthorServiceImpl.getInstance().read(id);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("Пожалуйста введите валидный идентификатор в формате больше 0");
        }
    }

    @Override
    public void update() {
        System.out.println("Введите идентификатор автора для обновления");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            System.out.println("Введите имя автора");
            String name = inputString();
            System.out.println("Введите фамилию автора");
            String surname = inputString();
            System.out.println("Введите список книг, если их несколько, разделите их запятой");
            String books = inputString();
            AuthorServiceImpl.getInstance().update(id, name, surname, books);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("Пожалуйста введите валидный идентификатор в формате больше 0");
        }
    }

    @Override
    public void delete() {
        System.out.println("Введите идентификатор автора для удаления");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            AuthorServiceImpl.getInstance().delete(id);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("Пожалуйста введите валидный идентификатор в формате больше 0");
        }
    }

    @Override
    public void findAll() {
        System.out.println("Введите идентификатор автора для получения всех книг");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            AuthorServiceImpl.getInstance().findAll(id);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("Пожалуйста введите валидный идентификатор в формате больше 0");
        }
    }

    @Override
    public void printTable() {
        AuthorServiceImpl.getInstance().printTable();
    }

    public static AuthorController getInstance() {
        if (instance == null) {
            instance = new AuthorController();
        }
        return instance;
    }
}
