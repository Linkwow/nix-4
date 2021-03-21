package demonstration.userinterface;

import reversestring.ChangeString;
import consolehelper.ConsoleHelper;

public class UserInterface {
    private static ConsoleHelper consoleHelper = new ConsoleHelper();

    private static void firstMethod(){
        System.out.println("Введите строку которую нужно обратить");
        consoleHelper.output(ChangeString.reverse(consoleHelper.input()));
    }

    private static void secondMethod(){
        System.out.println("Введите строку которую нужно обратить");
        String reverseString = consoleHelper.input();
        System.out.println("Введите последовательность символов котоую нужно развернуть во всех словах");
        String pattern = consoleHelper.input();
        consoleHelper.output(ChangeString.reverse(reverseString, pattern));
    }

    private static void thirdMethod(){
        System.out.println("Введите строку которую нужно обратить");
        String reverseString = consoleHelper.input();
        System.out.println("Введите первое число для диапазона");
        Integer first = Integer.parseInt(consoleHelper.input());
        System.out.println("Введите второе число для диапазона");
        Integer second = Integer.parseInt(consoleHelper.input());
        consoleHelper.output(ChangeString.reverse(reverseString, first, second));
    }

    private static boolean end(){
        System.out.println("Желаете продолжить?");
        System.out.println("1. Нет.");
        System.out.println("2. Да.");
        int end = Integer.parseInt(consoleHelper.input());
        if(end == 1){
            return  false;
        } else {
            return  true;
        }
    }

    public static void start(){
        do {
            firstMethod();
            secondMethod();
            thirdMethod();
        } while (end());
    }
}
