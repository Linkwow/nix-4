package bracketchecker.console;

import java.util.Scanner;

public class ConsoleInteraction {

    public static String input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("������� ������: ");
        return scanner.nextLine();
    }
}
