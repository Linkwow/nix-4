package ua.nix.bookslibrary.entity;

import java.util.*;

public class Author extends LibraryEntity {
    private Integer id;
    private String name;
    private String surname;
    private boolean visible = true;
    private final List<String> books = new ArrayList<>();
    private final List<Class<? extends Book>> bookList = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setBooks(String[] books) {
        this.books.clear();
        String[] temp = new String[books.length];
        for (int index = 0; index < books.length; index++) {
            if (books[index].charAt(0) == ' ') {
                temp[index] = books[index].substring(1, books[index].length() - 1);
            } else {
                temp[index] = books[index];
            }
        }
        this.books.addAll(Arrays.asList(temp));
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<String> getBooks() {
        return books;
    }

    public List<Class<? extends Book>> getBookList() {
        return bookList;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
