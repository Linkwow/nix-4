package ua.nix.bookslibrary.dao;

import ua.nix.bookslibrary.entity.Author;

public interface AuthorDao extends BaseDao<Author>{
    void update(Integer id, String name, String surname, String books);
}
