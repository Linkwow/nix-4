package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grades")
public class Grade extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "value", nullable = true)
    private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme")
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "students")
    private Student student;

    public Grade() {
    }

    public Long getId() {
        return id;
    }

    public Grade(String value, Student student, Theme theme) {
        this.value = Integer.parseInt(value);
        this.student = student;
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public Student getStudent() {
        return student;
    }
}
