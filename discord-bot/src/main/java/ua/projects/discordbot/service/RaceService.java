package ua.projects.discordbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.projects.discordbot.bot.SlashCommandCreator;
import ua.projects.discordbot.exceptions.EntityNotFoundException;
import ua.projects.discordbot.persistence.Race;
import ua.projects.discordbot.repository.CommonRepository;
import ua.projects.discordbot.repository.RaceRepository;

import java.util.List;

@Service
public class RaceService implements CommonRepository<Race> {

    private final RaceRepository repository;

    private SlashCommandCreator slashCommandCreator;

    @Autowired
    public void setSlashCommandCreator(SlashCommandCreator slashCommandCreator) {
        this.slashCommandCreator = slashCommandCreator;
    }

    public RaceService(RaceRepository repository) {
        this.repository = repository;
    }

    public Race create(String name) {
        Race race = repository.save(new Race(name));
        updateCommands();
        return race;
    }

    @Override
    public List<Race> findAll() {
        return repository.findAll();
    }

    @Override
    public Race find(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> EntityNotFoundException.notFound(id));
    }

    @SuppressWarnings("UnusedReturnValue")
    public Race update(Integer id, String name) {
        Race race = find(id);
        race.setName(name);
        repository.save(race);
        updateCommands();
        return race;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
        updateCommands();
    }

    @Override
    public void updateCommands() {
        slashCommandCreator.updateCommands();
    }
}
