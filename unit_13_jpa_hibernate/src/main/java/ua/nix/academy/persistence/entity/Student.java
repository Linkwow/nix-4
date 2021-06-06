package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
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
            inverseJoinColumns = @JoinColumn(name = "grades_id", referencedColumnName = "id")
    )
    private List<Grade> grades;


    public Student() {
    }

    public Student(String initials, Group group) {
        this.initials = initials;
        this.group = group;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}
