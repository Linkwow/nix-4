package ua.projects.discordbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.projects.discordbot.bot.SlashCommandCreator;
import ua.projects.discordbot.exceptions.EntityNotFoundException;
import ua.projects.discordbot.persistence.Faction;
import ua.projects.discordbot.persistence.Race;
import ua.projects.discordbot.repository.CommonRepository;
import ua.projects.discordbot.repository.FactionRepository;
import ua.projects.discordbot.repository.RaceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FactionService implements CommonRepository<Faction> {

    private final FactionRepository factionRepository;

    private RaceRepository raceRepository;

    private SlashCommandCreator slashCommandCreator;

    public FactionService(FactionRepository factionRepository){
        this.factionRepository = factionRepository;
    }

    @Autowired
    public void setSlashCommandCreator(SlashCommandCreator slashCommandCreator) {
        this.slashCommandCreator = slashCommandCreator;
    }

    @Autowired
    public void setRaceRepository(RaceRepository raceRepository){
        this.raceRepository = raceRepository;
    }

    @Transactional
    public Faction create(String name, String raceName){
        Faction faction = new Faction(name);
        Race race = raceRepository.findRaceByNameIs(raceName);
        faction.setRace(race);
        factionRepository.save(faction);
        updateCommands();
        return faction;
    }

    @Override
    public List<Faction> findAll(){
        return factionRepository.findAll();
    }

    @Override
    public Faction find(Integer id){
        return factionRepository.findById(id).orElseThrow(
                () -> EntityNotFoundException.notFound(id));
    }

    @SuppressWarnings("UnusedReturnValue")
    @Transactional
    public Faction update(Integer id, String name, String raceName){
        Faction faction = find(id);
        faction.setName(name);
        Race race  = raceRepository.findRaceByNameIs(
                Optional.of(raceName).orElse(faction.getName()));
        faction.setRace(race);
        factionRepository.save(faction);
        updateCommands();
        return faction;
    }

    @Override
    public void delete(Integer id){
        factionRepository.deleteById(id);
        updateCommands();
    }

    @Override
    public void updateCommands(){
        slashCommandCreator.updateCommands();
    }
}
