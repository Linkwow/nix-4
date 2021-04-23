package ua.nix.bookslibrary.service;

import ua.nix.bookslibrary.entity.BaseEntity;

public interface BaseService<T extends BaseEntity> {
    void read(Integer id);
    void delete(Integer id);
    void printTable();
    void findAll(Integer id);
}
