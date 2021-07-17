package ua.projects.discordbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.projects.discordbot.persistence.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

}
