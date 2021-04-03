package ua.nix.bookslibrary.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Author extends Entity {
    private String firstName;
    private String lastName;
    private final List<String> booksList = new ArrayList<>();
    private final List<Integer> bookId = new ArrayList<>();

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBookList(String[] books) {
        booksList.clear();
        Collections.addAll(booksList, books);
    }

    public List<String> getBooksList() {
        return booksList;
    }

    public void setBookId(int id){
        bookId.add(id);
    }

    public List<Integer> getBookId() {
        return bookId;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public void clear(){
        booksList.clear();
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + booksList;
    }
}