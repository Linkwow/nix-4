package ua.nix.collections.entity.baseentity;

public abstract class AbstractUser {
    protected String name;

    public AbstractUser(String name){
        this.name = name;
    }

    abstract public String toString();
}
