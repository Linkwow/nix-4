package ua.nix.bookslibrary.demodatabase;

import ua.nix.bookslibrary.controller.AuthorController;
import ua.nix.bookslibrary.controller.BookController;
import ua.nix.bookslibrary.service.implementation.*;
import ua.nix.bookslibrary.data.*;

public class DemoDataBase {
    private static DemoDataBase instance;

    private DemoDataBase() {
    }

    public void createDemoDataBase() {

        createBook("Граф_Монте-Кристо", "1846", "Александр Дюма");
        createBook("Гамлет", "1601", "Уильям Шекспир");
        createBook("Три_товарища", "1932", "Эрих-Мария Ремарк");
        createBook("Хроники_Нарнии", "1950", "Клайв-Стейплз Льюис", "Анна Нейл");
        createBook("Над_пропастью_во_ржи", "1846", "Джером-Дэвид Сэлинджер");
        createBook("Евгений_Онегин", "1830", "Александр Пушкин");
        createBook("Дубровский", "1830", "Александр Пушкин");
        BookController.getInstance().printTable();
        createAuthor("Александр", "Дюма", "Граф_Монте-Кристо 1846");
        createAuthor("Уильям", "Шекспир", "Гамлет 1601");
        createAuthor("Эрих-Мария", "Ремарк", "Три_товарища 1932");
        createAuthor("Клайв-Стейплз", "Льюис", "Хроники_Нарнии 1950");
        createAuthor("Джером-Дэвид", "Сэлинджер", "Над_пропастью_во_ржи 1846");
        createAuthor("Александр", "Пушкин", "Евгений_Онегин 1830", "Дубровский 1830");
        createAuthor("Анна", "Нейл", "Хроники_Нарнии 1950");
        AuthorController.getInstance().printTable();
    }

    private void createBook(String title, String year, String... author) {
        Book book = new Book();
        book.setTitle(title);
        book.setYear(year);
        book.setAuthorList(author);
        BookServiceImpl.getInstance().create(book);
    }

    private void createAuthor(String name, String surname, String... books) {
        Author author = new Author();
        author.setFirstName(name);
        author.setLastName(surname);
        author.setBookList(books);
        AuthorServiceImpl.getInstance().create(author);
    }

    public static DemoDataBase getInstance() {
        if (instance == null) {
            instance = new DemoDataBase();
        }
        return instance;
    }
}
