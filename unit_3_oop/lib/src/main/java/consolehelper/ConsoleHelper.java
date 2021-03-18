package consolehelper;

import java.util.Scanner;

public class ConsoleHelper implements Console {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String input() {
        return scanner.nextLine();
    }

    @Override
    public void output(Object obj) {
       System.out.println(obj);
    }


}
