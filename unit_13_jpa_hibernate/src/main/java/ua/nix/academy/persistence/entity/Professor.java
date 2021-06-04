package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Professors")
public class Professor extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "initials")
    private String initials;

    @ManyToMany
    @JoinTable(
            name = "professors_groups",
            joinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id")
    )
    private List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "professor")
    private List<Lesson> lessons = new ArrayList<>();
}
