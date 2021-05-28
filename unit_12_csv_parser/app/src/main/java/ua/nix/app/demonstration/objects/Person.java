package ua.nix.app.demonstration.objects;

import ua.nix.app.demonstration.annotatons.PersonAnnotation;

public class Person {
    @PersonAnnotation("personName")
    private String name;

    @PersonAnnotation("personAge")
    private int age;

    public String bio;

    @PersonAnnotation("familyStatus")
    public Boolean isMarried;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bio='" + bio + '\'' +
                ", isMarried=" + isMarried +
                '}';
    }
}