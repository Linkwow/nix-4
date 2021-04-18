package ua.nix.app.controller;

import ua.nix.libs.MathSet;

public class Controller {
    public static void run() {
        System.out.println("������������ ������������ ����������");
        add();
    }

    private static void add() {
        System.out.println("������������ ���������� ���������");
        MathSet mathSet = new MathSet();
        System.out.println("MathSet mathSet = new MathSet();");
        mathSet.add(12);
        System.out.println("mathSet.add(12);");
        mathSet.add(-2);
        System.out.println("mathSet.add(-2);");
        mathSet.add(156);
        System.out.println("mathSet.add(156);");
        mathSet.add(0);
        System.out.println("mathSet.add(0);");
        mathSet.add(4);
        System.out.println("mathSet.add(4);");
        mathSet.add(-7);
        System.out.println("mathSet.add(-7);");
        mathSet.add(4);
        System.out.println("mathSet.add(4);");
        System.out.println("��������� �������� ���:");
        for (Number i : mathSet.toArray()) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("��������������� �� �����������");
        System.out.println("mathSet.sortAsc();");
        mathSet.sortAsc();
        System.out.println(mathSet);
        System.out.println("��������������� �� ��������");
        System.out.println("mathSet.sortDesc();");
        mathSet.sortDesc();
        System.out.println(mathSet);
        System.out.println("��������������� �� ����������� c� ������� �� 5 ������");
        mathSet.sortAsc(2, 5);
        System.out.println(mathSet);
        System.out.println("������������ ��������");
        System.out.println(mathSet.getMax());
        System.out.println("�������");
        System.out.println(mathSet.getMedian());
        System.out.println("��� ��������� ����������� ���������� �� ������ ����������� ��������� ���������� � ����� ����");
        System.out.println("������� � pom ���� ");
        System.out.println("<repository>");
        System.out.println("   <id>myMavenRepoNaumenkoRead</id>");
        System.out.println("   <url>https://mymavenrepo.com/repo/7wnBpde3TYZTcEWly27I/</url>");
        System.out.println("</repository>");
        System.out.println("<dependency>");
        System.out.println("   <groupId>ua.nix.libs</groupId>");
        System.out.println("   <artifactId>mathset</artifactId>");
        System.out.println("   <version>1.0-SNAPSHOT</version>");
        System.out.println("</dependency>");

    }
}
