package ua.nix.hometasks.ui;

import ua.nix.hometasks.hippodrome.Hippodrome;
import ua.nix.hometasks.hippodrome.Horse;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class HippodromeInfoTable {

    private final Formatter formatter = new Formatter(System.out);
    private final Hippodrome hippodrome;

    public HippodromeInfoTable(Hippodrome hippodrome){
        this.hippodrome = hippodrome;
    }

    public void showHorses() {
        List<Horse> stable = hippodrome.getStable();
        formatter.format("%-4s%-20s%n", "Id", "Horse Name");
        for (Horse horse : stable) {
            formatter.flush();
            formatter.format("%-4d%-20s%n", horse.getId(), horse.getName());
        }
    }

    public void chooseHorse() {
        System.out.println("Please enter id of your horse.");
        Scanner scanner = new Scanner(System.in);
        int horseId = scanner.nextInt();
        hippodrome.chooseHorse(horseId);
    }

    public void getWinner(){
        CopyOnWriteArrayList<Horse> horses = hippodrome.getWinner();
        for(Horse horse : horses){
            if (horse.isChosen()){
                System.out.println("Your horse finished at position " + (horses.indexOf(horse) + 1));
            }
        }
    }
}
