package ua.nix.collections.entity;

import ua.nix.collections.entity.baseentity.AbstractUser;

public class User extends AbstractUser implements Comparable<User> {

    @Override
    public int compareTo(User objectToCompare) {
        int i = -1;
        if(this.stringValueCount() - objectToCompare.stringValueCount() == 0){
            i = 0;
        } else if(this.stringValueCount() - objectToCompare.stringValueCount() < 0){
            i = -1;
        } else if(this.stringValueCount() - objectToCompare.stringValueCount() > 0) {
            i = 1;
        }
        return i;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return this.getName().equals(user.getName());
    }

    public User(String name){
        super(name);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return  id;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
