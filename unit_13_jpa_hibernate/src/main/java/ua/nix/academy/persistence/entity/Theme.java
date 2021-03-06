package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Themes")
public class Theme extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "theme", fetch = FetchType.LAZY)
    List<Lesson> lessons = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme")
    private final List<Grade> grades = new ArrayList<>();

    public Theme() {
    }

    public Theme(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setGrades(Grade grade) {
        grades.add(grade);
        grade.setTheme(this);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }



    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
