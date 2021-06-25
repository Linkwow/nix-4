package ua.nix.hometasks.hippodrome;

import ua.nix.hometasks.ui.HippodromeInfoTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Race {

    public static void main(String[] args) {
        List<Horse> horseList = new ArrayList<>(Arrays.asList(
                        new Horse("Fast Rider"),
                        new Horse("Little Cherry"),
                        new Horse("Blind Bill"),
                        new Horse("Rocky Three Legs"),
                        new Horse("Big Tortoise")));
        Hippodrome hippodrome = new Hippodrome(horseList);
        HippodromeInfoTable hippodromeInfoTable = new HippodromeInfoTable(hippodrome);
        hippodromeInfoTable.showHorses();
        hippodromeInfoTable.chooseHorse();
        hippodrome.startRace();
        hippodrome.waitAll();
        hippodromeInfoTable.getWinner();
    }
}
