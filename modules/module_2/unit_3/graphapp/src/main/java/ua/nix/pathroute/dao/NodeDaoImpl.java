package ua.nix.pathroute.dao;

import ua.nix.libs.routecost.RouteCost;

public class NodeDaoImpl {
    private static NodeDaoImpl instance;

    public StringBuilder generate(String[] input) {
        return RouteCost.getInstance().startCountRouteCost(input);
    }

    public static NodeDaoImpl getInstance() {
        if (instance == null) {
            instance = new NodeDaoImpl();
        }
        return instance;
    }
}
