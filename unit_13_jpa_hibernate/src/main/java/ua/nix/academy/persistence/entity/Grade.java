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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "grades")
    private List<Theme> themes;

    public Grade(){}

    public Grade(String value){
        this.value = Integer.parseInt(value);
    }

    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }

    public String getValue() {
        return String.valueOf(this.value);
    }

    public void setThemes(Theme theme) {
        themes.add(theme);
    }

    @Override
    public String toString() {
        return "Grade{" + "\n" +
                "id=" + id + "\n" +
                ", value='" + value + "\n" +
                '}' + "\n";
    }
}
