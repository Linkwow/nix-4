package ua.projects.discordbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.projects.discordbot.persistence.Faction;

import java.util.List;

public interface FactionRepository extends JpaRepository<Faction, Integer> {

    Faction findFactionByNameIs(String factionName);

    List<Faction> findFactionByRaceIs(String race);

    boolean existsFactionByNameIs(String name);

}
