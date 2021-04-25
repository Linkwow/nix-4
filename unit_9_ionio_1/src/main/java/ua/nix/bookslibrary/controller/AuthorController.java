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
        System.out.println("������� ��� ������");
        String name = inputString();
        System.out.println("������� ������� ������");
        String surname = inputString();
        System.out.println("������� ������ ����, ���� �� ���������, ��������� �� �������");
        String books = inputString();
        AuthorServiceImpl.getInstance().create(name, surname, books);
    }

    @Override
    public void read() {
        System.out.println("������� ������������� ������ ��� ������");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            AuthorServiceImpl.getInstance().read(id);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("���������� ������� �������� ������������� � ������� ������ 0");
        }
    }

    @Override
    public void update() {
        System.out.println("������� ������������� ������ ��� ����������");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            System.out.println("������� ��� ������");
            String name = inputString();
            System.out.println("������� ������� ������");
            String surname = inputString();
            System.out.println("������� ������ ����, ���� �� ���������, ��������� �� �������");
            String books = inputString();
            AuthorServiceImpl.getInstance().update(id, name, surname, books);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("���������� ������� �������� ������������� � ������� ������ 0");
        }
    }

    @Override
    public void delete() {
        System.out.println("������� ������������� ������ ��� ��������");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            AuthorServiceImpl.getInstance().delete(id);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("���������� ������� �������� ������������� � ������� ������ 0");
        }
    }

    @Override
    public void findAll() {
        System.out.println("������� ������������� ������ ��� ��������� ���� ����");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            AuthorServiceImpl.getInstance().findAll(id);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("���������� ������� �������� ������������� � ������� ������ 0");
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
