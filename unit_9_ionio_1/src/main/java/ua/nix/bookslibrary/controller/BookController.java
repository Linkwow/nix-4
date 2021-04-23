package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.service.impl.BookServiceImpl;

import static ua.nix.libs.Console.inputInt;
import static ua.nix.libs.Console.inputString;

public class BookController implements BaseController {
    private static BookController instance;

    private BookController(){}

    @Override
    public void create() {
        System.out.println("Введите название книги");
        String title = inputString();
        System.out.println("Введите имя и фамилию автора, если их несколько разделите их запятой");
        String authors = inputString();
        BookServiceImpl.getInstance().create(title, authors);
    }

    @Override
    public void read() {
        System.out.println("Введите идентификатор книги для вывода");
        Integer id = inputInt();
        BookServiceImpl.getInstance().read(id);
    }

    @Override
    public void update() {
        System.out.println("Введите идентификатор книги для обновления");
        Integer id = inputInt();
        System.out.println("Введите название книги");
        String title = inputString();
        System.out.println("Введите имя и фамилию автора, если их несколько разделите их запятой");
        String authors = inputString();
        BookServiceImpl.getInstance().update(id, title, authors);
    }

    @Override
    public void delete() {
        System.out.println("Введите идентификатор книги для удаления");
        Integer id = inputInt();
        BookServiceImpl.getInstance().delete(id);
    }

    @Override
    public void findAll() {
        System.out.println("Введите идентификатор книги для получения всех авторов");
        Integer id = inputInt();
        BookServiceImpl.getInstance().findAll(id);
    }

    @Override
    public void printTable() {
        BookServiceImpl.getInstance().printTable();
    }

    public static BookController getInstance() {
        if(instance == null){
            instance = new BookController();
        }
        return instance;
    }
}
