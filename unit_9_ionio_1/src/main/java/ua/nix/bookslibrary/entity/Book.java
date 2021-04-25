package ua.nix.bookslibrary.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book extends BaseEntity {
    private String title;
    private final List<String> authors = new ArrayList<>();
    private boolean visible = true;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setAuthors(String[] authors){
        this.authors.clear();
        this.authors.addAll(Arrays.asList(authors));
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String isVisible() {
        return String.valueOf(visible);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", visible=" + visible +
                '}';
    }
}