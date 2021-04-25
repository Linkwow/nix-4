package ua.nix.bookslibrary.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Author extends BaseEntity {
    private String name;
    private String surname;
    private final List<String> books = new ArrayList<>();
    private boolean visible = true;

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
        this.books.addAll(Arrays.asList(books));
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

    public String isVisible() {
        return String.valueOf(visible);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", books=" + books +
                ", visible=" + visible +
                '}';
    }
}