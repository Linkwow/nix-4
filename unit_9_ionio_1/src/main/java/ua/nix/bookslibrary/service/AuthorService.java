package ua.nix.bookslibrary.service;

import java.util.List;

import ua.nix.bookslibrary.entity.BaseEntity;
import ua.nix.bookslibrary.entity.Book;

public interface AuthorService<T extends BaseEntity> extends BaseService<Book>{
    void create(String name, String surname, String books);
    void update(Integer id, String name, String surname, String books);
    List<T> getAll();
}