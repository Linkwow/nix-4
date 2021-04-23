package ua.nix.bookslibrary.entity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Book extends BaseEntity {
    private Integer id;
    private String title;
    private boolean visible = true;
    private final List<String> authors = new LinkedList<>();
    private final List<Class<? extends Author>> authorList = new LinkedList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setAuthors(String[] authors){   //перенести в сервисы
        this.authors.clear();
        this.authors.addAll(Arrays.asList(authors));
    }

    public void setAuthorList(Author author){
        authorList.add(author.getClass());
        try {
            authorList.get(0).getMethod(toString());
        } catch (Exception e){
            System.err.println();
        }
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

    public List<Class<? extends Author>> getAuthorList() {
        return authorList;
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
