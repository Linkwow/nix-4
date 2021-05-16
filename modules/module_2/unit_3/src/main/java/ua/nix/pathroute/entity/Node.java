package ua.nix.pathroute.entity;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private static Integer count = 1;
    private final Integer index = count++;
    String townName;
    Integer neighborsNumber;
    List<Integer> neighbors = new ArrayList<>();
    List<Integer> routeCost = new ArrayList<>();

    public Integer getIndex() {
        return index;
    }

    public String getTownName() {
        return townName;
    }

    public List<Integer> getNeighbors() {
        return neighbors;
    }

    public List<Integer> getRouteCost() {
        return routeCost;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public void setNeighborsNumber(Integer neighborsNumber) {
        this.neighborsNumber = neighborsNumber;
    }

    public void setNeighbors(Integer neighbors) {
        this.neighbors.add(neighbors);
    }

    public void setRouteCost(Integer routeCost) {
        this.routeCost.add(routeCost);
    }
}
