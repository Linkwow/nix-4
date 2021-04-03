package ua.nix.bookslibrary.dao;

import ua.nix.bookslibrary.data.Book;

public interface BookDao extends MainDao<Book>{
    Book read(String title, String year);
    void update(int id, String title, String year, String[] authors);
    void getAuthorList(int id);
}