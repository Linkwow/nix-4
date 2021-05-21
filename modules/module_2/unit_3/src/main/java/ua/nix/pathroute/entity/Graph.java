package ua.nix.pathroute.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Graph {
    private List<Node> nodes = new ArrayList<>();
    private Integer startIndex;
    private Integer finishIndex;
    private ListIterator<Node> nodesIterator;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public int start(String[] townsName) {
        findRouteVertex(townsName);
        nodes.get(startIndex - 1).setWeight(0);
        linksVertex();
        return nodes.get(finishIndex-1).getWeight();
    }

    private void findRouteVertex(String[] townsName) {
        Node node;
        nodesIterator = nodes.listIterator();
        while (nodesIterator.hasNext()) {
            node = nodesIterator.next();
            if (townsName[0].equals(node.getTownName())) {
                startIndex = node.getVertex();
            }
        }
        while (nodesIterator.hasPrevious()) {
            node = nodesIterator.previous();
            if (townsName[1].equals(node.getTownName())) {
                finishIndex = node.getVertex();
            }
        }
    }

    private void linksVertex() {
        Node currentNode;
        Node neighborNode;
        Integer neighborVertex = 0;
        nodesIterator = nodes.listIterator();
        while (nodesIterator.hasNext()) {
            currentNode = nodesIterator.next();
            Iterator<Integer> currentNodeNeighborIterator = currentNode.getRoute().keySet().iterator();
            while (currentNodeNeighborIterator.hasNext()) {
                neighborVertex = currentNodeNeighborIterator.next();
                neighborNode = nodes.get(neighborVertex - 1);
                if (!neighborNode.isVisited()) {
                    if (currentNode.getWeight() + currentNode.getRoute().get(neighborVertex) < neighborNode.getWeight()){
                        neighborNode.setWeight(currentNode.getWeight() + currentNode.getRoute().get(neighborVertex));
                    }
                }
            }
            currentNode.setVisited(true);
        }
    }
}
