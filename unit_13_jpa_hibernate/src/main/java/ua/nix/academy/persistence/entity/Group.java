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

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Lesson> lessons = new ArrayList<>();

    public Group(){}

    public Group(String name, Course course, Professor professor){
        this.name = name;
        this.course = course;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudents(Student student) {
       students.add(student);
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setLessons(Lesson lesson) {
       lessons.add(lesson);
       lesson.setGroup(this);
    }


}
