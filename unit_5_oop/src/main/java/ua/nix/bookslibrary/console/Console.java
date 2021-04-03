package ua.nix.bookslibrary.console;

import java.util.Arrays;
import java.util.Scanner;

public class Console {

    public static String inputString() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.isEmpty() || s.isBlank()) {
            return "null";
        } else {
            return s.replaceAll(" ", "_");
        }
    }

    public static String[] inputAuthorList() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().split(",\\s*");
    }

    public static String[] inputBookList() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.isEmpty() || s.isBlank()) {
            return new String[]{"null"};
        } else {
            String[] array = s.split(",\\s*");
            for (int i = 0; i < array.length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(array[i].replaceAll(" ", "_"));
                sb.setCharAt(array[i].length() - 5, ' ');
                array[i] = sb.toString();
            }
            return array;
        }
    }

    public static int inputInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}