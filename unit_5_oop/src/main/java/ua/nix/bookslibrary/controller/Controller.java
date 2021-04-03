package ua.nix.bookslibrary.controller;

import ua.nix.bookslibrary.controller.BookController;
import ua.nix.bookslibrary.controller.AuthorController;

public class Controller {

    public static void createBook(){
        BookController.getInstance().create();
    }

    public static void createAuthor(){
        AuthorController.getInstance().create();
    }

    public static void printAuthorTable(){

    }




}
