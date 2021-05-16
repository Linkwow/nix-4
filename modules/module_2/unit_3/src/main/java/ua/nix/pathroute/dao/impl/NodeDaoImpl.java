package ua.nix.pathroute.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ua.nix.pathroute.dao.Dao;
import ua.nix.pathroute.entity.Node;

public class NodeDaoImpl implements Dao<Node> {
    private static NodeDaoImpl instance;

    public List<Node> generate(String[] input) {
        List<Node> nodes = new ArrayList<>();
        int countNodes = input.length - 1;
        for (int index = 0, position = 1; index < countNodes; index++, position++) {
            nodes.add(create(input[index].split("/")));
        }
        return nodes;
    }

    private Node create(String[] input) {
        Node node = new Node();
        node.setTownName(input[0]);
        int neighborsNumber = Integer.parseInt(input[1]);
        node.setNeighborsNumber(neighborsNumber);
        String[] neighborRouteCost;
        for (int index = 2; index < input.length; index++) {
            neighborRouteCost = input[index].split(" ");
            node.setNeighbors(Integer.parseInt(neighborRouteCost[0]));
            node.setRouteCost(Integer.parseInt(neighborRouteCost[1]));
        }
        return node;
    }

    public static NodeDaoImpl getInstance() {
        if (instance == null) {
            instance = new NodeDaoImpl();
        }
        return instance;
    }
}
