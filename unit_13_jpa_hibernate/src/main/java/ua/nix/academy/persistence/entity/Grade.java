package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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



    public Grade() {
    }

    public Long getId() {
        return id;
    }

    public Grade(String value, Theme theme) {
        this.value = Integer.parseInt(value);
        this.theme = theme;
    }

    public Integer getValue() {
        return value;
    }

    public Theme getTheme() {
        return theme;
    }
}
