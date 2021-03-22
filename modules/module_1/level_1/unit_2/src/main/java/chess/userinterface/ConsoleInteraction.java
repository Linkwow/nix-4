package chess.userinterface;

import chess.services.AppLogic;

import java.util.Scanner;

public class ConsoleInteraction {

    private static String input(){
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    public static String enterStartPosition(){
        System.out.println("Введите стартовую позицию фигуры в формате БЧ где Б - буква, Ч - число");
        return input();
    }

    public static String enterNextPosition(){
        System.out.println("Введите следующую позицию фигуры");
        return input();
    }

    public static String enough(){
        System.out.println("Вы желаете продолжить?");
        System.out.println("1. Нет");
        System.out.println("2. Да");
        return input();
    }
}