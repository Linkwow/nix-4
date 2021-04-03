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

        createBook("����_�����-������", "1846", "��������� ����");
        createBook("������", "1601", "������ �������");
        createBook("���_��������", "1932", "����-����� ������");
        createBook("�������_������", "1950", "�����-������� �����", "���� ����");
        createBook("���_���������_��_���", "1846", "������-����� ���������");
        createBook("�������_������", "1830", "��������� ������");
        createBook("����������", "1830", "��������� ������");
        BookController.getInstance().printTable();
        createAuthor("���������", "����", "����_�����-������ 1846");
        createAuthor("������", "�������", "������ 1601");
        createAuthor("����-�����", "������", "���_�������� 1932");
        createAuthor("�����-�������", "�����", "�������_������ 1950");
        createAuthor("������-�����", "���������", "���_���������_��_��� 1846");
        createAuthor("���������", "������", "�������_������ 1830", "���������� 1830");
        createAuthor("����", "����", "�������_������ 1950");
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
