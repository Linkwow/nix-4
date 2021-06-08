package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
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
    @JoinColumn(name = "groups")
    private Group group;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public Student(String initials, Group group) {
        this.initials = initials;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        group.setStudents(this);
    }

    public void setGrades(Grade grade) {
        grades.add(grade);
    }
}
