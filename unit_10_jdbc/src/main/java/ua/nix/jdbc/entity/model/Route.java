package ua.nix.jdbc.entity.model;

public class Route extends AbstractModel {
    private int id;
    private int fromId;
    private int toId;
    private int cost;

    public int getId() {
        return id;
    }

    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
    }

    public int getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
