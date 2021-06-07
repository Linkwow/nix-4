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

    @Column(name = "course_number", unique = true, nullable = false)
    private String courseNumber;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Group> groupList = new ArrayList<>();

    public Course() {

    }

    public Course(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Long getId() {
        return id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(Group group) {
        this.groupList.add(group);
        group.setCourse(this);
    }

    @Override
    public String toString() {
        return "Course{" + "\n" +
                "id=" + id + "\n" +
                ", courseNumber='" + courseNumber + "\n" +
                '}' + "\n";
    }
}
