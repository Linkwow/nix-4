package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "Lessons")
public class Lesson extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme")
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor")
    private Professor professor;

    @Column(name = "date_time")
    private ZonedDateTime zonedDateTime ;

    @ManyToMany(mappedBy = "lessons", fetch = FetchType.LAZY)
    private List<Student> students;

    public Lesson(){}

    public Lesson(ZonedDateTime zonedDateTime, Theme theme, Professor professor){
        this.zonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        this.theme = theme;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setStudents(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return "Lesson{" + "\n" +
                "id=" + id + "\n" +
                ", theme=" + theme + "\n" +
                ", professor=" + professor + "\n" +
                ", zonedDateTime=" + zonedDateTime +
                '}' + "\n";
    }
}
