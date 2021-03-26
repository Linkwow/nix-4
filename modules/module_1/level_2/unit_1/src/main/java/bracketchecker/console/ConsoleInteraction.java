package bracketchecker.console;

import java.util.Scanner;

public class ConsoleInteraction {

    public static String input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("¬ведите строку: ");
        return scanner.nextLine();
    }
}
