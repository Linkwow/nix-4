package ua.nix.bookslibrary.service;
import java.util.List;

public interface MainService<T> {
    void create(T entity);
    T read(int id);
    void delete(int id);
    List<T> findAll();
    List<T> getList(int id);
}
