package ua.nix.datestandart.ui;

import ua.nix.libs.Console;

public class UserInterface {

    public static int menu(){
        System.out.println("Вы желаете сгенерировать даты автоматически или ввести вручную?" +
                "\n" + "1. Автоматическая генерация." + "\n" +
                "2. Ручной ввод.");
        return Console.inputInt();
    }
}