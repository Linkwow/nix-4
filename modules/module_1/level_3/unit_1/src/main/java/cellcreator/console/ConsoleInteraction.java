package cellcreator.console;

import java.util.Scanner;

public class ConsoleInteraction {

    public static String inputSize(){
        System.out.println("������� ����������� ���������� ���� � ����� ������ ����� ������");
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    public static int generationCount(){
        System.out.println("������� ���������� ���������");
        Scanner console = new Scanner(System.in);
        return console.nextInt();
    }
}