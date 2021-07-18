package ua.projects.discordbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.projects.discordbot.persistence.Unit;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

    List<Unit> getUnitByFactionAndCategory(String faction, String category);

}
