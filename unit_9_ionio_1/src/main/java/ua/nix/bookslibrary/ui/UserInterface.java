package ua.nix.bookslibrary.ui;

import static ua.nix.libs.Console.*;

import ua.nix.bookslibrary.controller.*;

public class UserInterface {

    public static void run() {
        boolean end = false;
        while (!end) {
            System.out.println("Выберите действие с БД:");
            System.out.println("1. Создать книгу.");
            System.out.println("2. Создать автора.");
            System.out.println("3. Вывести книгу по ID.");
            System.out.println("4. Вывести автора по ID.");
            System.out.println("5. Обновить информацию о книге по ID.");
            System.out.println("6. Обновить информацию о авторе по ID.");
            System.out.println("7. Удалить книгу.");
            System.out.println("8. Удалить автора.");
            System.out.println("9. Получить всех авторов по книге.");
            System.out.println("10. Получить все книги по автору");
            System.out.println("11. Вывести таблицу книг");
            System.out.println("12. Вывести таблицу авторов");
            System.out.println("13. Выход.");
            switch (inputInt()) {
                case 1:
                    BookController.getInstance().create();
                    break;
                case 2:
                    AuthorController.getInstance().create();
                    break;
                case 3:
                    BookController.getInstance().read();
                    break;
                case 4:
                    AuthorController.getInstance().read();
                    break;
                case 5:
                    BookController.getInstance().update();
                    break;
                case 6:
                    AuthorController.getInstance().update();
                    break;
                case 7:
                    BookController.getInstance().delete();
                    break;
                case 8:
                    AuthorController.getInstance().delete();
                    break;
                case 9:
                    BookController.getInstance().findAll();
                    break;
                case 10:
                    AuthorController.getInstance().findAll();
                    break;
                case 11:
                    BookController.getInstance().printTable();
                    break;
                case 12:
                    AuthorController.getInstance().printTable();
                    break;
                case 13:
                    end = true;
                    break;
                default:
                    break;
            }
        }
    }
}
