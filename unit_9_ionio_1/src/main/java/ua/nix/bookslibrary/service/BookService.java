package ua.nix.bookslibrary.service;

public interface BookService extends BaseService {
    void create(String title, String authors);
    void update(Integer id, String title, String authors);
}