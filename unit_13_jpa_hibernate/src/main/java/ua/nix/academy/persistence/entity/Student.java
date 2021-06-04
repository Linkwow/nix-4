package ua.nix.academy.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "Students")
public class Student extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "academy_generator")
    @SequenceGenerator(name = "academy_generator", sequenceName = "academy_sequence", allocationSize = 5)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "initials", nullable = false)
    private String initials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group", nullable = false)
    private Group group;

/*    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_lessons",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    )
    private List<Lesson> lessons;*/

    public Student(){}

    public Student(String initials, Group group){
        this.initials = initials;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public String getInitials() {
        return initials;
    }

    public Group getGroup() {
        return group;
    }
}
