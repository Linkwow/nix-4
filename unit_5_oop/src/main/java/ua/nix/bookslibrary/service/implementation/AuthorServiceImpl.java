package ua.nix.bookslibrary.service.implementation;

import ua.nix.bookslibrary.dao.implementation.AuthorDaoImpl;
import ua.nix.bookslibrary.data.Author;
import ua.nix.bookslibrary.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private static AuthorServiceImpl instance;

    private AuthorServiceImpl(){}

    @Override
    public void create(Author author) {
        AuthorDaoImpl.getInstance().create(author);
    }

    @Override
    public void update(int id, String firstName, String lastName, String[] books) {
        AuthorDaoImpl.getInstance().update(id, firstName, lastName, books);
    }

    @Override
    public void delete(int id) {
        AuthorDaoImpl.getInstance().delete(id);
    }

    @Override
    public Author read(int id) {
        return AuthorDaoImpl.getInstance().read(id);
    }

    @Override
    public Author read(String lastName, String firstName){
        return AuthorDaoImpl.getInstance().read(lastName, firstName);
    }

    @Override
    public List<Author> findAll() {
        return AuthorDaoImpl.getInstance().findAll();
    }

    @Override
    public void getBooksList(int id) {
        AuthorDaoImpl.getInstance().getBooksList(id);
    }

    @Override
    public List<Author> getList(int id) {
        return AuthorDaoImpl.getInstance().getList(id);
    }

    public static AuthorServiceImpl getInstance() {
        if(instance == null){
            instance = new AuthorServiceImpl();
        }
        return instance;
    }
}