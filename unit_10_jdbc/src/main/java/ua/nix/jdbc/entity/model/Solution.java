package ua.nix.jdbc.entity.model;

public class Solution extends AbstractModel {
    private int id;
    private int problemId;
    private int cost;

    public int getId() {
        return id;
    }

    public int getProblemId() {
        return problemId;
    }

    public int getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
