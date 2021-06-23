package ua.nix.threadexample;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Application {

    private volatile String input = "";
    private final Scanner scanner;
    private boolean stop;

    public Application(){
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        return input;
    }

    public boolean isStop() {
        return stop;
    }

    public void read() {
        String temp, check;
        while (true) {
            check = scanner.nextLine();
            temp = check.toLowerCase(Locale.ROOT);
            if(temp.equals("quit")) {
                stop = true;
                break;
            }
            this.input = check;
        }
    }

    public static void main(String[] args) {
        try {
            Application application = new Application();
            WriteToFile writeToFile = new WriteToFile(application);
            new Thread(writeToFile, "writeThread").start();
            application.read();
        } catch (IOException ioException){
            throw new RuntimeException(ioException);
        }
    }
}
