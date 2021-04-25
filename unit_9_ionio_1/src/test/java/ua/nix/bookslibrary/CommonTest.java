package ua.nix.bookslibrary;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ua.nix.bookslibrary.controller.AuthorController;
import ua.nix.bookslibrary.controller.BookController;
import ua.nix.bookslibrary.dao.impl.AuthorDaoImpl;
import ua.nix.bookslibrary.dao.impl.BookDaoImpl;
import ua.nix.bookslibrary.entity.Author;
import ua.nix.bookslibrary.entity.Book;
import ua.nix.bookslibrary.service.impl.AuthorServiceImpl;
import ua.nix.bookslibrary.service.impl.BookServiceImpl;

import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonTest {

    @Test
    @Order(1)
    public void createAuthorTableCheck() {
        String[] emptyArray = new String[]{" "};
        String firstName = "John";
        String lastName = "Smith#";
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            String[] bookArray = new String[]{"shadows 199" + i};
            author.setName(firstName);
            author.setSurname(lastName + i);
            if (i < 8) {
                author.setBooks(bookArray);
            } else {
                author.setBooks(emptyArray);
            }
            AuthorServiceImpl.getInstance().create(author.getName(), author.getSurname(), author.getBooks().toString());
        }
        Assert.assertTrue(AuthorDaoImpl.getInstance().readAll().size() == 11);
    }

    @Test
    @Order(2)
    public void createBookTableCheck() {
        String[] emptyArray = new String[]{""};
        String title = "shadows";
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            String[] authorArray = new String[]{"John Smith#" + i};
            book.setTitle(title);
            if (i < 8) {
                book.setAuthors(authorArray);
            } else {
                book.setAuthors(emptyArray);
            }
            BookServiceImpl.getInstance().create(book.getTitle(), book.getAuthors().toString());
        }
        Assert.assertTrue(AuthorDaoImpl.getInstance().readAll().size() == 11);
    }

    @Test
    @Order(3)
    public void printTableCheck() {
        BookController.getInstance().printTable();
        AuthorController.getInstance().printTable();
    }

    @Test
    @Order(4)
    public void readByIdForBookCheck() {
        for (Integer i = 1; i < BookDaoImpl.getInstance().readAll().size(); i++) {
            Assert.assertEquals(i, BookDaoImpl.getInstance().read(i).getId());
        }
    }

    @Test
    @Order(5)
    public void readByIdForAuthorCheck() {
        for (Integer i = 1; i < AuthorDaoImpl.getInstance().readAll().size(); i++) {
            Assert.assertEquals(i, AuthorDaoImpl.getInstance().read(i).getId());
        }
    }

    @Test
    @Order(6)
    public void updateBookCheck() {
        String title = "lights";
        for (Integer i = 1; i < BookDaoImpl.getInstance().readAll().size(); i++) {
            String[] authors = new String[]{"Nick Doomski#" + i};
            BookServiceImpl.getInstance().update(i, title, Arrays.toString(authors));
            Assert.assertEquals(i, BookDaoImpl.getInstance().read(i).getId());
            Assert.assertEquals(title, BookDaoImpl.getInstance().read(i).getTitle());
            Assert.assertEquals(authors[0], BookDaoImpl.getInstance().read(i).getAuthors().get(0));
        }
    }

    @Test
    @Order(7)
    public void updateAuthorCheck() {
        String firstName = "Nick";
        String lastName = "Doomski#";
        for (Integer i = 1; i < AuthorDaoImpl.getInstance().readAll().size(); i++) {
            lastName += i;
            String[] bookArray = new String[]{"lights 200" + i};
            AuthorServiceImpl.getInstance().update(i, firstName, lastName, Arrays.toString(bookArray));
            Assert.assertEquals(i, AuthorDaoImpl.getInstance().read(i).getId());
            Assert.assertEquals(firstName, AuthorDaoImpl.getInstance().read(i).getName());
            String test = AuthorDaoImpl.getInstance().read(i).getSurname();
            Assert.assertEquals(lastName, AuthorDaoImpl.getInstance().read(i).getSurname());
            Assert.assertEquals(bookArray[0], AuthorDaoImpl.getInstance().read(i).getBooks().get(0));
        }
    }

    @Test
    @Order(8)
    public void deleteBookCheck() {
        for (int i = 1; i < BookDaoImpl.getInstance().readAll().size(); i++) {
            BookServiceImpl.getInstance().delete(i);
        }
        for (Integer i = 1; i < BookDaoImpl.getInstance().readAll().size(); i++) {
            Assert.assertFalse(Boolean.parseBoolean(BookDaoImpl.getInstance().read(i).isVisible()));
        }
    }

    @Test
    @Order(9)
    public void deleteAuthorCheck() {
        for (int i = 1; i < AuthorDaoImpl.getInstance().readAll().size(); i++) {
            AuthorDaoImpl.getInstance().delete(i);
        }
        for (Integer i = 1; i < AuthorDaoImpl.getInstance().readAll().size(); i++) {
            Assert.assertFalse(Boolean.parseBoolean(AuthorDaoImpl.getInstance().read(i).isVisible()));
        }
    }
}