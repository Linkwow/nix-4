package consoleHelper;

import java.net.SocketOption;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;

public class ConsoleHelper {

    public void menu(){
        System.out.println("Выберите действие : ");
        System.out.print("1. Сложение двух чисел.");
        System.out.print("1. Вычитание двух чисел.");
        System.out.print("1. Сложение двух чисел.");
        System.out.print("1. Сложение двух чисел.");
        System.out.print("1. Сложение двух чисел.");
    }




    public void start(){
        Scanner input = new Scanner(System.in);
        System.out.println("Введите первое число : ");
        String firstNum = input.nextLine();
        System.out.println("Введите второе число : ");
        String secondNum = input.nextLine();
    }

    public static void main(String[] args) {
        ConsoleHelper jf = new ConsoleHelper();
        jf.start();

    }
}
