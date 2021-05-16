package ua.nix.datestandart.controller;

import ua.nix.datestandart.ui.UserInterface;
import ua.nix.datestandart.service.Service;
import ua.nix.libs.Console;

public class Controller {

    public static void start() {
        switch (UserInterface.menu()) {
            case 1:
                System.out.println("Âגוהטעו ךמכטקוסעמג האע הכÿ דוםונאצטט.");
                Service.getInstance().autoRun(Console.inputInt());
                break;
            case 2 :
                System.out.println("Âגוהטעו האעû ג פמנלאעו דדדד/לל/הה טכט הה/לל/דדדד טכט לל-הה-דדדד");
                Service.getInstance().manualRun(Console.inputLine());
        }
    }
}
