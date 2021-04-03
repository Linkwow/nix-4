package ua.nix.bookslibrary.console;

import java.util.Scanner;

public class Console {

    public static String inputString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String[] inputList(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().split(",\\s*");
    }

    public static int inputInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}