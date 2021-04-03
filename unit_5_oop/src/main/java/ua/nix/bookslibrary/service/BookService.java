package ua.nix.bookslibrary.service;

import ua.nix.bookslibrary.data.Book;

public interface BookService extends MainService<Book> {
    Book read(String title, String year);
    void update(int id, String title, String year, String[] authors);
}