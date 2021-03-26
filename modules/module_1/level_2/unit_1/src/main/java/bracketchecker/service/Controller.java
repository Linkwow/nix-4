package bracketchecker.service;

import static bracketchecker.console.ConsoleInteraction.input;
import static bracketchecker.logic.AppLogic.check;

public class Controller {

    public static void start(){
        if (check(input())){
            System.out.println("Строка допустима");
        } else {
            System.out.println("Строка не допустима");
        }
    }
}