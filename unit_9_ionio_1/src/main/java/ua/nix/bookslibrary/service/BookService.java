package ua.nix.bookslibrary.service;

import java.util.List;

import ua.nix.bookslibrary.entity.Author;
import ua.nix.bookslibrary.entity.BaseEntity;

public interface BookService<T extends BaseEntity> extends BaseService<Author> {
    void create(String title, String authors);
    void update(Integer id, String title, String authors);
    List<T> getAll();
}