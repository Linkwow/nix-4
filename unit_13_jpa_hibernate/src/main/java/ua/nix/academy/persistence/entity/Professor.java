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

    @OneToMany(mappedBy = "professor",fetch = FetchType.LAZY)
    private List<Group> groups;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Lesson> lessons = new ArrayList<>();

    public Professor(){}

    public Professor(String initials) {
        this.initials = initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}
