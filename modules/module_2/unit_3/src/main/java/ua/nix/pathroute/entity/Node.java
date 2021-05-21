package ua.nix.pathroute.entity;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Integer vertex;
    private String townName;
    private final Map<Integer, Integer> route = new HashMap<>();
    private int weight = 200000;
    private boolean visited;

    public Integer getVertex() {
        return vertex;
    }

    public String getTownName() {
        return townName;
    }

    public Map<Integer, Integer> getRoute() {
        return route;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVertex(Integer vertex) {
        this.vertex = vertex;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public void setRoute(Integer neighborVertex, Integer routeCost) {
        route.put(neighborVertex, routeCost);
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
