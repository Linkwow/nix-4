package ua.projects.discordbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.projects.discordbot.persistence.Faction;

import java.util.List;

public interface FactionRepository extends JpaRepository<Faction, Integer> {

   // @Query("select f from Faction f where f.name in (:factionsNames)")
    List<Faction> findFactionByNameIn(List<String> name);

}
