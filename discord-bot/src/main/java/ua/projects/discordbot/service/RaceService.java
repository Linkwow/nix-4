package ua.projects.discordbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.projects.discordbot.Exceptions.RaceNotFoundException;
import ua.projects.discordbot.bot.SlashCommandCreator;
import ua.projects.discordbot.persistence.Faction;
import ua.projects.discordbot.persistence.Race;
import ua.projects.discordbot.repository.RaceRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RaceService  {

    private RaceRepository repository;

    private SlashCommandCreator slashCommandCreator;

    @Autowired
    public void setSlashCommandCreator(SlashCommandCreator slashCommandCreator) {
        this.slashCommandCreator = slashCommandCreator;
    }

    public RaceService(RaceRepository repository){
        this.repository = repository;
    }

    public void createRace(Race race) {
        repository.save(race);
        updateCommands();
    }

    public Race findRaceById(Integer id) {
        return repository.findById(id).orElseThrow(() -> RaceNotFoundException.notFound(id));
    }

    public List<Race> findAllRaces(){
        List<Race> raceList = new ArrayList<>();
        repository.findAll().forEach(raceList::add);
        return raceList;
    }

    public void update(Integer id, String name, List<Faction> factionList) {
        Race race = findRaceById(id);
        race.setName(name);
        for (Faction faction : factionList)
            race.setFactionList(faction);
        updateCommands();
    }

    public void deleteById(Integer id) {
        Race race = findRaceById(id);
        repository.delete(race);
        updateCommands();
    }

    public void updateCommands(){
        slashCommandCreator.updateCommands();
    }
}
