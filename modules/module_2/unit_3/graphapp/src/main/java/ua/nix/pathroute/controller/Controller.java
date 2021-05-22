package ua.nix.pathroute.controller;

import ua.nix.pathroute.service.Service;

public class Controller {

    public static void start(){
        Service.getInstance().start();
    }
}