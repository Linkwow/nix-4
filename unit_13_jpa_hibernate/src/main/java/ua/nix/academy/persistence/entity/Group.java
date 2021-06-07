package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor")
    private Professor professor;

    public Group(){}

    public Group(String name, Course course, Professor professor){
        this.name = name;
        this.course = course;
        this.professor = professor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudents(Student student) {
       students.add(student);
       student.setGroup(this);
    }

    public Long getId() {
        return id;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    @Override
    public String toString() {
        return "Group{" + "\n" +
                "id=" + id + "\n" +
                ", name='" + name + "\n" +
                ", course=" + course + "\n" +
                '}' + "\n";
    }
}
