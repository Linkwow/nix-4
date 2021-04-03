package ua.nix.bookslibrary.dao.implementation;

import ua.nix.bookslibrary.dao.AuthorDao;
import ua.nix.bookslibrary.data.Author;
import ua.nix.bookslibrary.db.DataBase;

import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private static AuthorDaoImpl instance;

    private AuthorDaoImpl(){}

    @Override
    public void create(Author author) {
        DataBase.getInstance().createAuthor(author);
    }

    @Override
    public Author read(int id) {
        return DataBase.getInstance().findAuthorById(id);
    }

    @Override
    public Author read(String lastName, String firstName){
        return DataBase.getInstance().findAuthorByInitials(lastName, firstName);
    }

    @Override
    public void update(int id, String firstName, String lastName, String[] books) {
        DataBase.getInstance().updateAuthor(id, firstName, lastName, books);
    }

    @Override
    public void delete(int id) {
        DataBase.getInstance().deleteAuthor(id);
    }

    @Override
    public List<Author> findAll() {
        return DataBase.getInstance().findAllAuthors();
    }

    @Override
    public void getBooksList(int id) {
        DataBase.getInstance().getBookList(id);
    }

    @Override
    public List<Author> getList(int id) {
        DataBase.getInstance().createRelationshipsAuthorsToBook();
        return DataBase.getInstance().getAllAuthorsByBook(id);
    }

    public static AuthorDaoImpl getInstance() {
        if(instance == null){
            instance = new AuthorDaoImpl();
        }
        return instance;
    }
}