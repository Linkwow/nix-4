package ua.projects.discordbot.repository;

import org.springframework.data.repository.CrudRepository;
import ua.projects.discordbot.persistence.Race;

public interface RaceRepository extends CrudRepository<Race, Integer> {

}
