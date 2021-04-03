package ua.nix.bookslibrary.dao;

import ua.nix.bookslibrary.data.Entity;
import java.util.List;

public interface MainDao<T extends Entity> {

    void create(T entity);
    T read(int id);
    void delete(int id);
    List<T> findAll();
    List<T> getList(int id);
}