package ua.nix.bookslibrary.service;

public interface BaseService {
    void read(Integer id);
    void delete(Integer id);
    void printTable();
    void findAll(Integer id);
}
