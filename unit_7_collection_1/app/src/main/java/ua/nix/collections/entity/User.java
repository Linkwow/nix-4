package ua.nix.collections.entity;

import ua.nix.collections.entity.baseentity.AbstractUser;

public class User extends AbstractUser implements Comparable<User> {

    @Override
    public int compareTo(User objectToCompare) {
        int i = -1;
        if(this.getName().equals(objectToCompare.getName())){
            i = 0;
        } else if(this.getName().compareToIgnoreCase(objectToCompare.getName()) < 0){
            i = -1;
        } else if(this.getName().compareToIgnoreCase(objectToCompare.getName()) > 0) {
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

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}