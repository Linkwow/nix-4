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
    private String value;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "grades")
    private List<Student> student;

    public Grade(){}

    public Grade(String value){
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
