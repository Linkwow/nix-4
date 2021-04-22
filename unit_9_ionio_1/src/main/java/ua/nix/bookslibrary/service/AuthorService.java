package ua.nix.bookslibrary.service;

import ua.nix.bookslibrary.entity.Book;

public interface AuthorService extends BaseService<Book>{
    void create(String name, String surname, String books);
    void update(Integer id, String name, String surname, String books);
}