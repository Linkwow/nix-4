package ua.projects.discordbot.repository;

import org.springframework.data.repository.CrudRepository;
import ua.projects.discordbot.persistence.Faction;

public interface FactionRepository extends CrudRepository<Faction, Integer> {

}
