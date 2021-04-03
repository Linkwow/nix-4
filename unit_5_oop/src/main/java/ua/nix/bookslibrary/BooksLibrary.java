package ua.nix.bookslibrary;

import ua.nix.bookslibrary.demodatabase.DemoDataBase;
import ua.nix.bookslibrary.ui.UserInterface;

public class BooksLibrary {

    public static void main(String[] args) {
        DemoDataBase.getInstance().createDemoDataBase();
        new UserInterface().run();
    }
}
