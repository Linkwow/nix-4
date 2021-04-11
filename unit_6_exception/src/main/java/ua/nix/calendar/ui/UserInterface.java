package ua.nix.calendar.ui;

import ua.nix.calendar.controller.Controller;
import ua.nix.calendar.exceptions.DateException;

import static ua.nix.calendar.console.Console.*;
import static ua.nix.calendar.console.Console.inputString;

public class UserInterface {
    private boolean end;
    String menuChoice;

    public void run() throws DateException {
        while (!end) {
            try {
                System.out.println("Выберите дальнейшие действия : ");
                System.out.println("1. Ввести дату.");
                System.out.println("2. Найти разницу между датами в миллисекундах, секундах, минутах, часах, годах и веках.");
                System.out.println("3. Прибавить к дате миллисекунды, секунды, минуты, часы, года и века.");
                System.out.println("4. Вычесть из даты миллисекунды, секунды, минуты, часы, года и века.");
                System.out.println("5. Сравнить перечень введённых дат по возрастанию.");
                System.out.println("6. Сравнить перечень введённых дат по убыванию.");
                System.out.println("7. Выход.");
                menuChoice = inputString();
                if (menuChoice == null || Integer.parseInt(menuChoice) < 1 || Integer.parseInt(menuChoice) > 7) {
                    throw new DateException();
                }
            } catch (Exception e) {
                System.err.println("Извините но вы ввели не корректный номер, введите корректный номер.");
                run();
            }
            switch (Integer.parseInt(menuChoice)) {
                case 1:
                    System.out.println("Введите дату в формате : ");
                    System.out.println("dd/mm/yy.");
                    System.out.println("m/d/yyyy.");
                    System.out.println("mmm-d-yy.");
                    System.out.println("dd-mmm-yyyy hh:mm.");
                    try {
                        Controller.getInstance().create(inputString());
                    } catch (DateException d) {
                        System.err.println(d.getMessage());
                        run();
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Введите первый id");
                        int firstId = inputInt();
                        if (firstId == 0) {
                            throw new DateException("Извините но введите идентификатор больше 0.");
                        }
                        System.out.println("Введите второй id");
                        int secondId = inputInt();
                        if (secondId == 0) {
                            throw new DateException("Извините но введите идентификатор больше 0.");
                        }
                        Controller.getInstance().difference(firstId, secondId);
                    } catch (DateException d) {
                        System.err.println(d.getMessage());
                        run();
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Введите первый id");
                        int firstId = inputInt();
                        if (firstId == 0) {
                            throw new DateException("Извините но введите идентификатор больше 0.");
                        }
                        System.out.println("Введите второй id");
                        int secondId = inputInt();
                        if (secondId == 0) {
                            throw new DateException("Извините но введите идентификатор больше 0.");
                        }
                        Controller.getInstance().add(firstId, secondId);
                    } catch (DateException d) {
                        System.err.println(d.getMessage());
                        run();
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Введите первый id");
                        int firstId = inputInt();
                        if (firstId == 0) {
                            throw new DateException("Извините но введите идентификатор больше 0.");
                        }
                        System.out.println("Введите второй id");
                        int secondId = inputInt();
                        if (secondId == 0) {
                            throw new DateException("Извините но введите идентификатор больше 0.");
                        }
                        Controller.getInstance().subtract(firstId, secondId);
                    } catch (DateException d) {
                        System.err.println(d.getMessage());
                        run();
                    }
                    break;
                case 5:
                    System.out.println("Выберите формат для вывода: ");
                    System.out.println("1.dd/mm/yy.");
                    System.out.println("2.m/d/yyyy.");
                    System.out.println("3.mmm-d-yy.");
                    System.out.println("4.dd-mmm-yyyy.");
                    try {
                        Controller.getInstance().ascSort(inputInt());
                    } catch (DateException d) {
                        System.err.println(d.getMessage());
                        run();
                    }
                    break;
                case 6:
                    System.out.println("Выберите формат для вывода: ");
                    System.out.println("1.dd/mm/yy.");
                    System.out.println("2.m/d/yyyy.");
                    System.out.println("3.mmm-d-yy.");
                    System.out.println("4.dd-mmm-yyyy.");
                    try {
                        Controller.getInstance().descSort(inputInt());
                    } catch (DateException d) {
                        System.err.println(d.getMessage());
                        run();
                    }
                    break;
                case 7:
                    end = true;
                default:
                    break;
            }
        }
    }
}
