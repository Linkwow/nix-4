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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "grades", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "themes", referencedColumnName = "id")
    )
    private List<Grade> grades;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "themes")
    private List<Student> students;

    public Theme(){}

    public Theme(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setLessons(Lesson lesson) {
        lessons.add(lesson);
        lesson.setTheme(this);
    }

    public void setGrades(Grade grade) {
        grades.add(grade);
        grade.setThemes(this);
    }

    public void setStudents(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return "Theme{" + "\n" +
                "id=" + id + "\n" +
                ", name='" + name + "\n" +
                '}' + "\n";
    }
}
