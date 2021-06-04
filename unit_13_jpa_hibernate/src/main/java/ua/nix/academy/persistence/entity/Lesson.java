package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Lessons")
public class Lesson extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theme")
    private Theme theme;

    @ManyToOne
    @JoinColumn(name = "professor")
    private Professor professor;

    @Column(name = "date_time")
    private ZonedDateTime zonedDateTime;
}
