package ua.projects.discordbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.projects.discordbot.persistence.Unit;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

}
