package cellcreator.console;

import java.util.Scanner;

public class ConsoleInteraction {

    public static String inputSize(){
        System.out.println("¬ведите размерность клеточного пол€ в целых числах через пробел");
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    public static int generationCount(){
        System.out.println("¬ведите количество поколений");
        Scanner console = new Scanner(System.in);
        return console.nextInt();
    }
}