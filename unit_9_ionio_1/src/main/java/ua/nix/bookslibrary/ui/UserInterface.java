package ua.nix.bookslibrary.ui;

import static ua.nix.libs.Console.*;

import ua.nix.bookslibrary.controller.*;

import java.util.InputMismatchException;

public class UserInterface {

    public static void run() {
        boolean end = false;
        while (!end) {
            System.out.println("�������� �������� � ��:");
            System.out.println("1. ������� �����.");
            System.out.println("2. ������� ������.");
            System.out.println("3. ������� ����� �� ID.");
            System.out.println("4. ������� ������ �� ID.");
            System.out.println("5. �������� ���������� � ����� �� ID.");
            System.out.println("6. �������� ���������� � ������ �� ID.");
            System.out.println("7. ������� �����.");
            System.out.println("8. ������� ������.");
            System.out.println("9. �������� ���� ������� �� �����.");
            System.out.println("10. �������� ��� ����� �� ������");
            System.out.println("11. ������� ������� ����");
            System.out.println("12. ������� ������� �������");
            System.out.println("13. �����.");
            try {
                int choice = inputInt();
                if (choice < 0) {
                    throw new InputMismatchException();
                }
                switch (choice) {
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
            } catch (InputMismatchException inputMismatchException) {
                System.err.println("���������� ������� ��������� ����� � ������� ������ 0");
            }
        }
    }
}
