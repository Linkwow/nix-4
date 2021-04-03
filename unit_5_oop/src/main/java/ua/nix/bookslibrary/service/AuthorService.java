package ua.nix.bookslibrary.service;

import ua.nix.bookslibrary.data.Author;

public interface AuthorService extends MainService<Author>{
    Author read(String lastName, String firstName);
    void update(int id, String firstName, String lastName, String[] books);
}
