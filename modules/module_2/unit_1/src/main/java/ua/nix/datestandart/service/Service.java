package ua.nix.datestandart.service;

import ua.nix.datestandart.entity.generator.Generator;
import ua.nix.datestandart.service.parser.Parser;

public class Service {
    private static Service instance;

    public void autoRun(int quantity){
       parseFile(Generator.getInstance().generateRandomData(quantity));
    }

    public void manualRun(String input){
        parseFile(input);
    }

    private void parseFile(String inputData) {
        System.out.println("\n" + "���� ������� �������� �������� ������ : " + "\n" + Parser.getInstance().receive(inputData));
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
}
