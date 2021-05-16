package ua.nix.names.controller;

import ua.nix.names.service.Service;

public class Controller {
    public static void start(){
        System.out.println("Первое уникальное имя " + Service.getInstance().start());
    }
}
