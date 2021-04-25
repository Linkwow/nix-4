package ua.nix.bookslibrary.service;

public interface AuthorService extends BaseService{
    void create(String name, String surname, String books);
    void update(Integer id, String name, String surname, String books);
}