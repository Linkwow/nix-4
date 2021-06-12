package ua.nix.academy.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "grades")
public class Grade extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme")
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private Student student;

    public Grade() {
    }

    public Long getId() {
        return id;
    }

    public Grade(String value, Theme theme, Student student) {
        this.value = Integer.parseInt(value);
        this.theme = theme;
        this.student = student;
    }

    public Integer getValue() {
        return value;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
