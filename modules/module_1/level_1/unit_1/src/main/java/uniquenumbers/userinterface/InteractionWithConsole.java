package uniquenumbers.userinterface;

import java.util.Scanner;

public class InteractionWithConsole {
    private static Scanner console = new Scanner(System.in);

    public static String[] input(){
        System.out.println("������� ������ ����� ����� ������");
        String[] inputArray = console.nextLine().split(" ");
        return inputArray;
    }

    public static void output(int number){
        System.out.println("���������� ���������� �������� � ������� : " + number);
    }
}
