package ua.nix.jdbc.entity.model;

public class Problem extends AbstractModel {
    private int id;
    private int fromId;
    private int toId;

    public int getId() {
        return id;
    }

    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
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
}
