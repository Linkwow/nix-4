package ua.nix.bookslibrary.dao;

import ua.nix.bookslibrary.entity.Book;

public interface BookDao extends BaseDao<Book> {
    void update(Integer id, String title, String authors);
}
