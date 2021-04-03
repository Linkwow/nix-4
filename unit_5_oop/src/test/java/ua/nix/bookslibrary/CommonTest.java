package ua.nix.bookslibrary;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ua.nix.bookslibrary.controller.AuthorController;
import ua.nix.bookslibrary.controller.BookController;
import ua.nix.bookslibrary.data.Author;
import ua.nix.bookslibrary.data.Book;
import ua.nix.bookslibrary.service.implementation.AuthorServiceImpl;
import ua.nix.bookslibrary.db.DataBase;
import ua.nix.bookslibrary.service.implementation.BookServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonTest {
    DataBase db = DataBase.getInstance();

    @Test
    @Order(1)
    public void createAuthorTableCheck() {
        String[] emptyArray = new String[]{" "};
        String firstName = "John";
        String lastName = "Smith#";
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            String[] bookArray = new String[]{"shadows 199" + i};
            author.setFirstName(firstName);
            author.setLastName(lastName + i);
            if (i < 8) {
                author.setBookList(bookArray);
            } else {
                author.setBookList(emptyArray);
            }
            AuthorServiceImpl.getInstance().create(author);
        }
        Assert.assertTrue(db.findAllAuthors().size() > 0);
    }

    @Test
    @Order(2)
    public void createBookTableCheck() {
        String[] emptyArray = new String[]{""};
        String title = "shadows";
        String year = "199";
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            String[] authorArray = new String[]{"John Smith#" + i};
            book.setTitle(title);
            book.setYear(year + i);
            if (i < 8) {
                book.setAuthorList(authorArray);
            } else {
                book.setAuthorList(emptyArray);
            }
            BookServiceImpl.getInstance().create(book);
        }
        Assert.assertTrue(db.findAllBooks().size() > 0);
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
        for (int i = 1; i < db.findAllBooks().size(); i++) {
            Assert.assertEquals(i, BookServiceImpl.getInstance().read(i).getId());
        }
    }

    @Test
    @Order(5)
    public void readByIdForAuthorCheck() {
        for (int i = 1; i < db.findAllAuthors().size(); i++) {
            Assert.assertEquals(i, AuthorServiceImpl.getInstance().read(i).getId());
        }
    }

    @Test
    @Order(6)
    public void readByTitleForBookCheck() {
        for (int i = 1; i < db.findAllBooks().size(); i++) {
            Assert.assertEquals(i,
                    BookServiceImpl.getInstance().read(
                            BookServiceImpl.getInstance().read(i).getTitle(),
                            BookServiceImpl.getInstance().read(i).getYear()).getId());
        }
    }

    @Test
    @Order(7)
    public void readByTitleForAuthorCheck() {
        for (int i = 1; i < db.findAllAuthors().size(); i++) {
            Assert.assertEquals(i, AuthorServiceImpl.getInstance().read(
                    AuthorServiceImpl.getInstance().read(i).getLastName(),
                    AuthorServiceImpl.getInstance().read(i).getFirstName()).getId());
        }
    }

    @Test
    @Order(6)
    public void updateBookCheck() {
        String title = "lights";
        String year = "200";
        for (int i = 1; i < db.findAllBooks().size(); i++) {
            year += i;
            String[] authors = new String[]{"Nick Doomski#" + i};
            BookServiceImpl.getInstance().update(i, title, year, authors);
            Assert.assertEquals(i, BookServiceImpl.getInstance().read(i).getId());
            Assert.assertEquals(title, BookServiceImpl.getInstance().read(i).getTitle());
            Assert.assertEquals(year, BookServiceImpl.getInstance().read(i).getYear());
            Assert.assertEquals(authors[0], BookServiceImpl.getInstance().read(i).getAuthorList().get(0));
        }
    }

    @Test
    @Order(7)
    public void updateAuthorCheck() {
        String firstName = "Nick";
        String lastName = "Doomski#";
        for (int i = 1; i < db.findAllAuthors().size(); i++) {
            lastName += i;
            String[] bookArray = new String[]{"lights 200" + i};
            AuthorServiceImpl.getInstance().update(i, firstName, lastName, bookArray);
            Assert.assertEquals(i, AuthorServiceImpl.getInstance().read(i).getId());
            Assert.assertEquals(firstName, AuthorServiceImpl.getInstance().read(i).getFirstName());
            Assert.assertEquals(lastName, AuthorServiceImpl.getInstance().read(i).getLastName());
            Assert.assertEquals(bookArray[0], AuthorServiceImpl.getInstance().read(i).getBooksList().get(0));
        }
    }

    @Test
    @Order(8)
    public void deleteBookCheck() {
        for (int i = 0; !db.findAllBooks().isEmpty(); i++) {
            BookServiceImpl.getInstance().delete(i);
        }
    }

    @Test
    @Order(9)
    public void deleteAuthorCheck() {
        for (int i = 0; !db.findAllAuthors().isEmpty(); i++) {
            AuthorServiceImpl.getInstance().delete(i);
        }
        Assert.assertTrue(db.findAllAuthors().size() == 0);
    }
}