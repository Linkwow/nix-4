package ua.nix.collections.controller;

import ua.nix.collections.entity.User;
import ua.nix.libs.OrderedList;
import ua.nix.libs.Console;

public class Controller {

    public static void run() {
        System.out.println("������������ ������������ ����������");
        add();
    }

    private static void add() {
        System.out.println("������������ ���������� � �������������� ���������");
        OrderedList<Integer> integerList = new OrderedList<>();
        System.out.println("List<Integer> integerList = new OrderedList<>();");
        integerList.add(12);
        System.out.println("integerList.add(12);");
        integerList.add(-2);
        System.out.println("integerList.add(-2);");
        integerList.add(156);
        System.out.println("integerList.add(156);");
        integerList.add(0);
        System.out.println("integerList.add(0);");
        integerList.add(4);
        System.out.println("integerList.add(4);");
        integerList.add(-7);
        System.out.println("integerList.add(-7);");
        integerList.add(46);
        System.out.println("integerList.add(46);");
        System.out.println("������ �������� ���:");
        for (Integer i : integerList) {
            System.out.print(i + " ");
        }
        System.out.println();
        integerList = new OrderedList<>();
        System.out.println("���������� ����, ������� ������������� ��������(��� ������������ ������ ������������� ������ ������������� ��������, �������� �� ��������.");
        String[] input = Console.inputString(" ");
        try {
            for (String string : input) {
                Integer integer = Integer.parseInt(string);
                integerList.add(integer);
            }
            for (Integer i : integerList) {
                System.out.print(i + " ");
            }
        } catch (NumberFormatException numberFormatException) {
            System.err.println("������������ ��������� ��� ������ ������������� ��������");
        }
        System.out.println();
        System.out.println("������������ ���������� ��������");
        OrderedList<User> userList = new OrderedList<>();
        User admin = new User("�������");
        System.out.println("User admin = new User(\"�������\");");
        userList.add(admin);
        System.out.println("userList.add(admin);");
        User user = new User("����");
        System.out.println("User user = new User(\"����\");");
        userList.add(user);
        System.out.println("userList.add(user);");
        User superUser = new User("�������");
        System.out.println("User superUser = new User(\"������\");");
        userList.add(superUser);
        System.out.println("userList.add(superUser);");
        for (User u : userList) {
            System.out.print(u + " ");
        }
        System.out.println();
        System.out.println("��� ��������� ����������� ���������� �� ������ ������������� ");
        System.out.println("������� � pom ���� ");
        System.out.println("<repository>");
        System.out.println("   <id>myMavenRepoNaumenkoRead</id>");
        System.out.println("   <url>https://mymavenrepo.com/repo/7wnBpde3TYZTcEWly27I/</url>");
        System.out.println("</repository>");
        System.out.println("<dependency>");
        System.out.println("   <groupId>ua.nix.libs</groupId>");
        System.out.println("   <artifactId>orderedlist</artifactId>");
        System.out.println("   <version>1.0-SNAPSHOT</version>");
        System.out.println("</dependency>");
    }
}
