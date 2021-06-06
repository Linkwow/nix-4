package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "Lessons")
public class Lesson extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme")
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor")
    private Professor professor;

    @Column(name = "date_time")
    private ZonedDateTime zonedDateTime ;

    @ManyToMany(mappedBy = "lessons", fetch = FetchType.LAZY)
    private List<Student> students;

    public Lesson(){}

    public Lesson(ZonedDateTime zonedDateTime, Theme theme, Professor professor){
        this.zonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        this.theme = theme;
        this.professor = professor;
    }


}
