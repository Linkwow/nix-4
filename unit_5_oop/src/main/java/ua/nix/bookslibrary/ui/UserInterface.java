package ua.nix.bookslibrary.ui;

import ua.nix.bookslibrary.controller.Controller;

import java.util.Scanner;

public class UserInterface {
    private boolean end;
    private final Scanner scanner = new Scanner(System.in);

    public void run(){
        while(!end) {
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
            switch (scanner.nextInt()){
                case 1:
                    Controller.createBook();
                    break;
                case 2:
                    Controller.createAuthor();
                    break;
                case 3:
                    Controller.readBookById();
                    break;
                case 4:
                    Controller.readAuthorById();
                    break;
                case 5:
                    Controller.updateBook();
                    break;
                case 6:
                    Controller.updateAuthor();
                    break;
                case 7:
                    Controller.deleteBook();
                    break;
                case 8:
                    Controller.deleteAuthor();
                    break;
                case 9:
                    Controller.getAuthorsByBook();
                    break;
                case 10:
                    Controller.getBooksByAuthor();
                    break;
                case 11:
                    Controller.printBookTable();
                    break;
                case 12:
                    Controller.printAuthorTable();
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
