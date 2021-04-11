package ua.nix.calendar;
import ua.nix.calendar.ui.UserInterface;

public class Calendar {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        try {
            ui.run();
        } catch (Exception d){
            System.err.println(d.getMessage());
        }
    }
}
