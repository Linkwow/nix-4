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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "races_generator")
    @SequenceGenerator(name = "races_generator", sequenceName = "races_sequence", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Short id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "race", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Faction> factionList = new ArrayList<>();

    public Race() {
    }

    public Race(String name) {
        this.name = name;
    }

    public Short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Faction> getFactionList() {
        return factionList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFactionList(Faction faction) {
        factionList.add(faction);
    }
}
