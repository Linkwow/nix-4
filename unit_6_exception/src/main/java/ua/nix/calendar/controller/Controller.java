package ua.nix.calendar.controller;

import ua.nix.calendar.exceptions.DateException;
import ua.nix.calendar.service.impl.ServiceImpl;;

public class Controller {

    private static Controller instance;

    public void create(String input) throws DateException {
        try {
            ServiceImpl.getInstance().createDate(input);
        } catch (DateException d){
            throw new DateException(d.getMessage());
        }
    }

    public void difference(int firstId, int secondId) throws DateException{
        try {
            ServiceImpl.getInstance().difference(firstId, secondId);
        } catch (DateException d){
            throw new DateException(d.getMessage());
        }
    }

    public void add(int firstId, int secondId) throws DateException {
        try {
            ServiceImpl.getInstance().add(firstId, secondId);
        } catch (DateException d){
            throw new DateException(d.getMessage());
        }
    }

    public void subtract(int firstId, int secondId) throws DateException {
        try {
            ServiceImpl.getInstance().subtract(firstId, secondId);
        } catch (DateException d){
            throw new DateException(d.getMessage());
        }
    }

    public void ascSort(int choice) throws DateException {
        try {
            ServiceImpl.getInstance().ascSort();
            ServiceImpl.getInstance().output(choice);
        } catch (Exception d){
            throw new DateException(d.getMessage());
        }
    }

    public void descSort(int choice) throws DateException{
        try {
            ServiceImpl.getInstance().descSort();
            ServiceImpl.getInstance().output(choice);
        } catch (DateException d){
            throw new DateException(d.getMessage());
        }
    }


    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
}