package ua.nix.bookslibrary.controller;

public class Controller {

    public static void createBook(){
        BookController.getInstance().create();
    }

    public static void createAuthor(){
        AuthorController.getInstance().create();
    }

    public static void printBookTable(){
        BookController.getInstance().printTable();
    }

    public static void printAuthorTable(){
        AuthorController.getInstance().printTable();
    }

    public static void readBookById(){
        BookController.getInstance().read();
    }

    public static void readAuthorById(){
        AuthorController.getInstance().read();
    }

    public static void updateBook(){
        BookController.getInstance().update();
    }

    public static void updateAuthor(){
        AuthorController.getInstance().update();
    }

    public static void deleteBook(){
        BookController.getInstance().delete();
    }

    public static void deleteAuthor(){
        AuthorController.getInstance().delete();
    }

    public static void getBooksByAuthor(){
        BookController.getInstance().geBooksByAuthor();
    }

    public static void getAuthorsByBook(){
        AuthorController.getInstance().getAuthorsByBook();
    }
}
