package demonstration.userinterface;

import reversestring.ChangeString;
import consolehelper.ConsoleHelper;

public class UserInterface {
    private static ConsoleHelper consoleHelper = new ConsoleHelper();

    private static void firstMethod(){
        System.out.println("������� ������ ������� ����� ��������");
        consoleHelper.output(ChangeString.reverse(consoleHelper.input()));
    }

    private static void secondMethod(){
        System.out.println("������� ������ ������� ����� ��������");
        String reverseString = consoleHelper.input();
        System.out.println("������� ������������������ �������� ������ ����� ���������� �� ���� ������");
        String pattern = consoleHelper.input();
        consoleHelper.output(ChangeString.reverse(reverseString, pattern));
    }

    private static void thirdMethod(){
        System.out.println("������� ������ ������� ����� ��������");
        String reverseString = consoleHelper.input();
        System.out.println("������� ������ ����� ��� ���������");
        Integer first = Integer.parseInt(consoleHelper.input());
        System.out.println("������� ������ ����� ��� ���������");
        Integer second = Integer.parseInt(consoleHelper.input());
        consoleHelper.output(ChangeString.reverse(reverseString, first, second));
    }

    private static boolean end(){
        System.out.println("������� ����������?");
        System.out.println("1. ���.");
        System.out.println("2. ��.");
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
