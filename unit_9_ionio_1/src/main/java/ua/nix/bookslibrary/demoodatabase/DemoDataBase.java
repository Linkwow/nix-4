package ua.nix.bookslibrary.demoodatabase;

import ua.nix.bookslibrary.service.impl.AuthorServiceImpl;
import ua.nix.bookslibrary.service.impl.BookServiceImpl;

public class DemoDataBase {

    public static void createDemoDataBase(){
        createAuthor("Александр", "Дюма", "Граф Монте-Кристо");
        createAuthor("Уильям", "Шекспир", "Гамлет");
        createAuthor("Эрих-Мария", "Ремарк", "Три товарища");
        createAuthor("Клайв-Стейплз", "Льюис", "Хроники Нарнии");
        createAuthor("Джером-Дэвид", "Сэлинджер", "Над пропастью во ржи");
        createAuthor("Александр", "Пушкин", "Евгений Онегин, Дубровский");
        createAuthor("Анна", "Нейл", "Хроники Нарнии");
        printAuthor();
        System.out.println();
        System.out.println();
        createBook("Граф Монте-Кристо", "Александр Дюма");
        createBook("Гамлет", "Уильям Шекспир");
        createBook("Три товарища", "Эрих-Мария Ремарк");
        createBook("Хроники Нарнии", "Клайв-Стейплз Льюис, Анна Нейл");
        createBook("Над пропастью во ржи", "Джером-Дэвид Сэлинджер");
        createBook("Евгений Онегин", "Александр Пушкин");
        createBook("Дубровский", "Александр Пушкин");
        printBook();
        System.out.println();
        System.out.println();
    }

    private static void createAuthor(String name, String surname, String books){
        AuthorServiceImpl.getInstance().create(name, surname, books);
    }

    private static void printAuthor(){
        AuthorServiceImpl.getInstance().printTable();
    }

    private static void createBook(String title, String authors){
        BookServiceImpl.getInstance().create(title, authors);
    }

    private static void printBook(){
        BookServiceImpl.getInstance().printTable();
    }
}