package ua.nix.bookslibrary;

import static ua.nix.bookslibrary.demoodatabase.DemoDataBase.*;
import static ua.nix.bookslibrary.ui.UserInterface.*;

public class BooksLibrary {
    public static void main(String[] args) {
        createDemoDataBase();
        run();
    }
}
