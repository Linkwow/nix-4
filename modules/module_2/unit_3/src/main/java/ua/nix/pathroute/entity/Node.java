package ua.nix.pathroute.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    private Integer vertex;
    String townName;
    Integer neighborsNumber;
    /*List<Integer> neighbors = new ArrayList<>();
    List<Integer> routeCost = new ArrayList<>();*/
    Map<Integer, Integer> route = new HashMap<>();
    private int weight = 200000;
    private boolean visited;

    public Integer getVertex() {
        return vertex;
    }

    public String getTownName() {
        return townName;
    }

    public int getWeight() {
        return weight;
    }

    /*    public List<Integer> getNeighbors() {
        return neighbors;
    }

    public List<Integer> getRouteCost() {
        return routeCost;
    }*/

    public void setVertex(Integer vertex) {
        this.vertex = vertex;
    }

    public void setRoute(Integer neighborVertex, Integer routeCost) {
       route.put(neighborVertex, routeCost);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public void setNeighborsNumber(Integer neighborsNumber) {
        this.neighborsNumber = neighborsNumber;
    }

/*    public void setNeighbors(Integer neighbors) {
        this.neighbors.add(neighbors);
    }

    public void setRouteCost(Integer routeCost) {
        this.routeCost.add(routeCost);
    }*/

    public Map<Integer, Integer> getRoute() {
        return route;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
