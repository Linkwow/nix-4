package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Lessons")
public class Lesson extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_time", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime zonedDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme")
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups")
    private Group group;

    public Lesson() {

    }

    public Lesson(ZonedDateTime zonedDateTime, Theme theme, Group group) {
        this.zonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        this.theme = theme;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public Theme getTheme() {
        return theme;
    }

    public Group getGroup() {
        return group;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }


}
