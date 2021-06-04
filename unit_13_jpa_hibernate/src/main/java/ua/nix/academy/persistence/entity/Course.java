package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "academy_generator")
    @SequenceGenerator(name = "academy_generator", sequenceName = "academy_sequence", allocationSize = 5)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "course_number", unique = true, nullable = false, updatable = false)
    private Integer courseNumber;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Group> groupList = new ArrayList<>();

    public Course(){}

    public Course(Integer courseNumber){
        this.courseNumber = courseNumber;
    }

    public Long getId() {
        return id;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public List<Group> getGroupList() {
        return groupList;
    }
}
