package ua.nix.libs.routecost;

import java.util.ArrayList;
import java.util.List;

import ua.nix.libs.entity.*;

public class RouteCost {

    private static RouteCost instance;

    public StringBuilder startCountRouteCost(String[] input){
        List<Node> nodes;
        String findTown = input[input.length - 1];
        String[] towns = findTown.split("/");
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j < towns.length; j++) {
            nodes = generate(input);
            String[] name = towns[j].split(" ");
            sb.append(result(nodes, name));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb;
    }

    private List<Node> generate(String[] input) {
        List<Node> nodes = new ArrayList<>();
        int countNodes = input.length - 1;
        for (int index = 0, vertex = 1; index < countNodes; index++, vertex++) {
            nodes.add(create(input[index].split("/"), vertex));
        }
        return nodes;
    }

    private int result(List<Node> nodes, String[] cityNames) {
        Graph graph = new Graph(nodes);
        return graph.start(cityNames);
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

    public static RouteCost getInstance() {
        if (instance == null) {
            instance = new RouteCost();
        }
        return instance;
    }
}