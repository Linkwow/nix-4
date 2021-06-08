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
    private final List<Group> groups = new ArrayList<>();

    public Professor(){}

    public Professor(String initials) {
        this.initials = initials;
    }

    public Long getId() {
        return id;
    }

    public void setGroups(Group group) {
        groups.add(group);
        group.setProfessor(this);
    }

    public String getInitials() {
        return initials;
    }
}
