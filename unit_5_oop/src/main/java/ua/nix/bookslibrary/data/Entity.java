package ua.nix.bookslibrary.data;

public abstract class Entity {
    protected int id;
    abstract public int getId();
    abstract public void setId(int id);
    abstract public String toString();
    abstract void clear();
}