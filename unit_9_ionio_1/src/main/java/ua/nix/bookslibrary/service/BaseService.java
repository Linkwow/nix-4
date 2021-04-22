package ua.nix.bookslibrary.service;

import java.util.List;

public interface BaseService<T> {
    void read(Integer id);
    void delete(Integer id);
    void printTable();
    List<Class<? extends T>> findAll();
}
