package chess.userinterface;

import chess.services.AppLogic;

import java.util.Scanner;

public class ConsoleInteraction {

    private static String input(){
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    public static String enterStartPosition(){
        System.out.println("������� ��������� ������� ������ � ������� �� ��� � - �����, � - �����");
        return input();
    }

    public static String enterNextPosition(){
        System.out.println("������� ��������� ������� ������");
        return input();
    }

    public static String enough(){
        System.out.println("�� ������� ����������?");
        System.out.println("1. ���");
        System.out.println("2. ��");
        return input();
    }
}