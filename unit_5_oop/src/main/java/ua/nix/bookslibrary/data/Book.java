package ua.nix.bookslibrary.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book extends Entity {

    private String title;
    private String year;
    private final List<String> authorList = new ArrayList<>();
    private final List<Integer> authorId = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setAuthorList(String[] authors) {
        authorList.clear();
        Collections.addAll(authorList, authors);
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setAuthorId(int id) {
        authorId.add(id);
    }

    public List<Integer> getAuthorId() {
        return authorId;
    }

    @Override
    public void clear() {
        authorList.clear();
    }

    @Override
    public String toString() {
        return id + " " + title + " " + year + " " + authorList;
    }
}