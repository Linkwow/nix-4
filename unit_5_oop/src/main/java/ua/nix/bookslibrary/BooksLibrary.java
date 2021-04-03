package ua.nix.bookslibrary;

import ua.nix.bookslibrary.controller.AuthorController;
import ua.nix.bookslibrary.controller.BookController;
import ua.nix.bookslibrary.data.Book;

public class BooksLibrary {

    public static void main(String[] args) {
        AuthorController.getInstance().create();
        AuthorController.getInstance().create();
        AuthorController.getInstance().printTable();
        BookController.getInstance().create();
        BookController.getInstance().create();
        BookController.getInstance().printTable();
        AuthorController.getInstance().getAuthorsByBook();
    }
}
