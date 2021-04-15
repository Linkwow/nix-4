package ua.nix.collections.entity.baseentity;

public abstract class AbstractUser {
    protected Integer id;
    protected String name;

    public AbstractUser(String name){
        this.name = name;
    }

    protected int stringValueCount(){
        int count = 0;
        for (Character c : this.name.toCharArray()){
            count += c;
        }
        return count;
    }

    abstract public String toString();
}
