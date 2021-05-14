package ua.nix.datestandart.controller;

import ua.nix.datestandart.ui.UserInterface;
import ua.nix.datestandart.service.Service;
import ua.nix.libs.Console;

public class Controller {

    public static void start() {
        switch (UserInterface.menu()) {
            case 1:
                System.out.println("¬ведите количество дат дл€ генерации");
                Service.getInstance().autoRun(Console.inputInt());
                break;
            case 2 :
                System.out.println("¬ведите строковую последовательность дат");
                Service.getInstance().manualRun(Console.inputLine());
        }

    }
}
