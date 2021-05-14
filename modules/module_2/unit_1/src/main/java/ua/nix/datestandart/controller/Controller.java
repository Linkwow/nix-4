package ua.nix.datestandart.controller;

import ua.nix.datestandart.ui.UserInterface;
import ua.nix.datestandart.service.Service;
import ua.nix.libs.Console;

public class Controller {

    public static void start() {
        switch (UserInterface.menu()) {
            case 1:
                System.out.println("������� ���������� ��� ��� ���������");
                Service.getInstance().autoRun(Console.inputInt());
                break;
            case 2 :
                System.out.println("������� ��������� ������������������ ���");
                Service.getInstance().manualRun(Console.inputLine());
        }

    }
}
