package ua.projects.discordbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.projects.discordbot.Exceptions.RaceNotFoundException;
import ua.projects.discordbot.bot.SlashCommandCreator;
import ua.projects.discordbot.persistence.Race;
import ua.projects.discordbot.repository.FactionRepository;
import ua.projects.discordbot.repository.RaceRepository;

import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    private FactionRepository factionRepository;

    private SlashCommandCreator slashCommandCreator;

    @Autowired
    public void setSlashCommandCreator(SlashCommandCreator slashCommandCreator) {
        this.slashCommandCreator = slashCommandCreator;
    }

    @Autowired
    public void setFactionRepository(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
    }

    public RaceService(RaceRepository repository) {
        this.raceRepository = repository;
    }

    public Race createRace(String name) {
        Race raceToCreate = raceRepository.save(new Race(name));
        updateCommands();
        return raceToCreate;
    }

    public List<Race> findAllRaces() {
        return raceRepository.findAll();
    }

    public Race findRaceById(Integer id) {
        return raceRepository.findById(id).orElseThrow(() -> RaceNotFoundException.notFound(id));
    }

    public Race updateByID(Integer id, String name) {
        Race raceToUpdate = findRaceById(id);
        raceToUpdate.setName(name);
        raceRepository.save(raceToUpdate);
        return raceToUpdate;
    }

    public void deleteById(Integer id) {
        Race raceToDelete = raceRepository.findById(id).orElseThrow(() -> RaceNotFoundException.notFound(id));
        raceRepository.delete(raceToDelete);
        updateCommands();
    }

    public void updateCommands() {
        slashCommandCreator.updateCommands();
    }
}
