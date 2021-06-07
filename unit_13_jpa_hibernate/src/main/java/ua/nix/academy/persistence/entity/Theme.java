package ua.nix.academy.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Themes")
public class Theme extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "theme", fetch = FetchType.LAZY)
    List<Lesson> lessons = new ArrayList<>();

    public Theme(){}

    public Theme(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
