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
        for (int index = 0, vertex = 1; index < countNodes; index++, vertex++) {
            nodes.add(create(input[index].split("/"), vertex));
        }
        return nodes;
    }

    private Node create(String[] input, int vertex) {
        Node node = new Node();
        node.setVertex(vertex);
        node.setTownName(input[0]);
        String[] neighborRouteCost;
        for (int index = 2; index < input.length; index++) {
            neighborRouteCost = input[index].split(" ");
            node.setRoute(Integer.parseInt(neighborRouteCost[0]), Integer.parseInt(neighborRouteCost[1]));
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
