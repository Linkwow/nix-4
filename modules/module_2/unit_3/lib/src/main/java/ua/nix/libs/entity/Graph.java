package ua.nix.libs.entity;

import java.util.*;

public class Graph {
    private final List<Node> nodes;
    private Integer startIndex;
    private Integer finishIndex;
    private final PriorityQueue<Node> priorityVertex = new PriorityQueue<>(idComparator);
    private final PriorityQueue<Node> temporaryQueue = new PriorityQueue<>(idComparator);
    private static final Comparator<Node> idComparator = Comparator.comparingInt(Node::getWeight);

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public int start(String[] townsName) {
        findRouteVertex(townsName);
        nodes.get(startIndex - 1).setWeight(0);
        priorityVertex.addAll(nodes);
        linksVertex();
        return nodes.get(finishIndex - 1).getWeight();
    }

    private void findRouteVertex(String[] townsName) {
        Node node;
        for (Node value : nodes) {
            node = value;
            if (townsName[0].equals(node.getTownName())) {
                startIndex = node.getVertex();
            } else if (townsName[1].equals(node.getTownName())) {
                finishIndex = node.getVertex();
            }
        }
    }

    private void linksVertex() {
        Node currentNode;
        Node neighborNode;
        Integer neighborVertex;
        for (int index = 0; index < priorityVertex.size(); index++) {
            currentNode = priorityVertex.poll();
            for (Integer integer : currentNode.getRoute().keySet()) {
                neighborVertex = integer;
                neighborNode = nodes.get(neighborVertex - 1);
                if (!neighborNode.isVisited()) {
                    if (currentNode.getWeight() + currentNode.getRoute().get(neighborVertex) < neighborNode.getWeight()) {
                        neighborNode.setWeight(currentNode.getWeight() + currentNode.getRoute().get(neighborVertex));
                    }
                }
            }
            currentNode.setVisited(true);
            temporaryQueue.addAll(priorityVertex);
            priorityVertex.clear();
            priorityVertex.addAll(temporaryQueue);
            temporaryQueue.clear();
        }
    }

    public List<Node> getNodes(){
        return nodes;
    }
}