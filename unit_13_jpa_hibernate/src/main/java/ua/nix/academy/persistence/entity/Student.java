package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Students")
public class Student extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "academy_generator")
    @SequenceGenerator(name = "academy_generator", sequenceName = "academy_sequence", allocationSize = 5)
    @Column(name = "id")
    private Long id;

    @Column(name = "initials")
    private String initials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    )
    private List<Lesson> lessons;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id", referencedColumnName = "id")
    )
    private List<Theme> themes;


    public Student() {
    }

    public Student(String initials, Group group) {
        this.initials = initials;
        this.group = group;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setLessons(Lesson lesson) {
        lessons.add(lesson);
    }

    public void setThemes(Theme theme) {
        themes.add(theme);
        theme.setStudents(this);
    }

    @Override
    public String toString() {
        return "Student{" + "\n" +
                "id=" + id + "\n" +
                ", initials='" + initials + "\n" +
                ", group=" + group + "\n" +
                '}' + "\n";
    }
}
