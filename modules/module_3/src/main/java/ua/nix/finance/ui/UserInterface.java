package ua.nix.finance.ui;

import ua.nix.finance.controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static void start(String[] args) {
        String[] dbParameters = new String[3];
        dbParameters[0] = args[0];
        dbParameters[1] = args[1];
        dbParameters[2] = args[2];
        System.out.println("""
                Enter number of operation:
                1. Create transaction;
                2. Get discharge;
                """);
        int providerChoice = new Scanner(System.in).nextInt();
        if(providerChoice == 1){
            List<String> entityParameters = new ArrayList<>();
            System.out.println("Enter amount of transactions.");
            entityParameters.add(new Scanner(System.in).next());
            System.out.println("Enter date of transaction in format yyyy-mm-ddThh:mm:ss");
            entityParameters.add(new Scanner(System.in).next());
            System.out.println("Enter category of transaction. If category more than one separate them using space");
            String[] category = new Scanner(System.in).next().split(" ");
            for (String cat : category){
                entityParameters.add(cat);
            }
            Controller.getInstance().providerStart(providerChoice, dbParameters, entityParameters.toArray(new String[0]));
        } else if (providerChoice == 2){
            String[] dateToDischarge = new String[2];
            System.out.println("Enter start date for discharge in format yyyy-mm-ddThh:mm:ss");
            dateToDischarge[0] = new Scanner(System.in).next();
            System.out.println("Enter end date for discharge in format yyyy-mm-ddThh:mm:ss");
            dateToDischarge[1] = new Scanner(System.in).next();
            Controller.getInstance().providerStart(providerChoice, dbParameters, dateToDischarge);
        }
    }
}
