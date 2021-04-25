package ua.nix.bookslibrary.demoodatabase;

import ua.nix.bookslibrary.service.impl.AuthorServiceImpl;
import ua.nix.bookslibrary.service.impl.BookServiceImpl;

public class DemoDataBase {

    public static void createDemoDataBase(){
        createAuthor("���������", "����", "���� �����-������");
        createAuthor("������", "�������", "������");
        createAuthor("����-�����", "������", "��� ��������");
        createAuthor("�����-�������", "�����", "������� ������");
        createAuthor("������-�����", "���������", "��� ��������� �� ���");
        createAuthor("���������", "������", "������� ������, ����������");
        createAuthor("����", "����", "������� ������");
        printAuthor();
        System.out.println();
        System.out.println();
        createBook("���� �����-������", "��������� ����");
        createBook("������", "������ �������");
        createBook("��� ��������", "����-����� ������");
        createBook("������� ������", "�����-������� �����, ���� ����");
        createBook("��� ��������� �� ���", "������-����� ���������");
        createBook("������� ������", "��������� ������");
        createBook("����������", "��������� ������");
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