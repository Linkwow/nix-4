package ua.nix.bookslibrary.dao;

import ua.nix.bookslibrary.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {
    void create(T t);
    T read(Integer id);
    List<String[]> readAll();
    void delete(Integer id);
    List<String> findAll(Integer id);
    List<T> takeAll(List<String> entities);
}