package ua.nix.jdbc.controller;

import ua.nix.jdbc.service.Service;
import ua.nix.libs.Console;

public class Controller {
    private static Controller instance;

    public void start(){
        Service service = new Service();
        System.out.println("Enter please number of routes/departure_city arrival_city" + "\n" +
                "i.e 2/poltava lutsk/lvov lutsk");
        String input = Console.inputLine();
        service.start(input);
    }

    public static Controller getInstance() {
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
}
