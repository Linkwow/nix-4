package ua.nix.bookslibrary.dao;

import ua.nix.bookslibrary.data.Author;

import java.util.List;

public interface AuthorDao extends MainDao<Author>{
    Author read(String lastName,  String firstName);
    void update(int id, String firstName, String lastName, String[] books);
    void getBooksList(int id);
}