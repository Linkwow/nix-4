package CountNums.services;

import consolehelper.ConsoleHelper;

public class Service {
    private ConsoleHelper consoleHelper = new ConsoleHelper();

    public String menuType(){
        System.out.println("�������� � ����� ����� ������ ����� ������������ �������� : ");
        System.out.println("1. Integer.");
        System.out.println("2. Double.");
        return consoleHelper.input();
    }

    public String menuOperations(){
        System.out.println("��������, ����� �������� �� ������� ����������� : ");
        System.out.println("1. ��������.");
        System.out.println("2. ���������.");
        System.out.println("3. ���������.");
        System.out.println("4. �������.");
        return consoleHelper.input();
    }

    public String firstNum(){
        System.out.println("������� ������ ����� : ");
        return consoleHelper.input();
    }

    public String secondNum(){
        System.out.println("������� ������ ����� : ");
        return consoleHelper.input();
    }

    public String end(){
        System.out.println("�� ������� ����������?");
        System.out.println("1. ��.");
        System.out.println("2. ���.");
        return consoleHelper.input();
    }
}