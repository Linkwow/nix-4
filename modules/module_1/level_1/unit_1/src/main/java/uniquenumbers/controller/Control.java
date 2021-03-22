package uniquenumbers.controller;
import uniquenumbers.service.AppLogic;
import uniquenumbers.userinterface.InteractionWithConsole;


public class Control {
    public static void run(){
        String[] input = InteractionWithConsole.input();
        Number[] numbersArray = AppLogic.parser(input);
        InteractionWithConsole.output(AppLogic.uniqueNumbers(numbersArray));
    }
}
