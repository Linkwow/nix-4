package ua.projects.discordbot.persistence;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "weapons")
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weapons_generator")
    @SequenceGenerator(name = "weapons_generator", sequenceName = "weapons_sequence", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Integer id;

    @NotNull
    @NotBlank
    @Column(name = "type")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "weaponType")
    private List<Unit> unitList = new ArrayList<>();

    public Weapon() {
    }

    public Weapon(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUnitList(Unit unit) {
        unitList.add(unit);
    }
}
