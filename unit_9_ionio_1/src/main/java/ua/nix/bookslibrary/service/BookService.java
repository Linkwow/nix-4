package ua.nix.bookslibrary.service;

import ua.nix.bookslibrary.entity.Author;

public interface BookService extends BaseService<Author> {
    void create(String title, String authors);
    void update(Integer id, String title, String authors);
}