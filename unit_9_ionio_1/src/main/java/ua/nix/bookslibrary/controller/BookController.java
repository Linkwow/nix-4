package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.service.impl.BookServiceImpl;

import java.util.InputMismatchException;

import static ua.nix.libs.Console.inputInt;
import static ua.nix.libs.Console.inputString;

public class BookController implements BaseController {
    private static BookController instance;

    private BookController(){}

    @Override
    public void create() {
        System.out.println("������� �������� �����");
        String title = inputString();
        System.out.println("������� ��� � ������� ������, ���� �� ��������� ��������� �� �������");
        String authors = inputString();
        BookServiceImpl.getInstance().create(title, authors);
    }

    @Override
    public void read() {
        System.out.println("������� ������������� ����� ��� ������");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            BookServiceImpl.getInstance().read(id);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("���������� ������� �������� ������������� � ������� ������ 0");
        }
    }

    @Override
    public void update() {
        System.out.println("������� ������������� ����� ��� ����������");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            System.out.println("������� �������� �����");
            String title = inputString();
            System.out.println("������� ��� � ������� ������, ���� �� ��������� ��������� �� �������");
            String authors = inputString();
            BookServiceImpl.getInstance().update(id, title, authors);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("���������� ������� �������� ������������� � ������� ������ 0");
        }
    }

    @Override
    public void delete() {
        System.out.println("������� ������������� ����� ��� ��������");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            BookServiceImpl.getInstance().delete(id);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("���������� ������� �������� ������������� � ������� ������ 0");
        }
    }

    @Override
    public void findAll() {
        System.out.println("������� ������������� ����� ��� ��������� ���� �������");
        try {
            int id = inputInt();
            if (id < 0) {
                throw new InputMismatchException();
            }
            BookServiceImpl.getInstance().findAll(id);
        } catch (InputMismatchException inputMismatchException) {
            System.err.println("���������� ������� �������� ������������� � ������� ������ 0");
        }
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
