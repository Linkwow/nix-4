package ua.nix.datestandart.ui;

import ua.nix.libs.Console;

public class UserInterface {

    public static int menu(){
        System.out.println("�� ������� ������������� ���� ������������� ��� ������ �������?" +
                "\n" + "1. �������������� ���������." + "\n" +
                "2. ������ ����.");
        return Console.inputInt();
    }
}