package ua.projects.discordbot.service;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.projects.discordbot.Exceptions.RaceNotFoundException;
import ua.projects.discordbot.Exceptions.JacksonInteractionException;
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

    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(RaceService.class);

    @Autowired
    public void setSlashCommandCreator(SlashCommandCreator slashCommandCreator) {
        this.slashCommandCreator = slashCommandCreator;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public RaceService(RaceRepository repository){
        this.repository = repository;
    }

    public void createRace(Race race) {
        repository.save(race);
        updateCommands();
    }

    public String findRaceById(Integer id) {
        Race race = repository.findById(id).orElseThrow(() -> RaceNotFoundException.notFound(id));
        String jsonRace;
        try {
            jsonRace = objectMapper.writeValueAsString(race);
        } catch (JacksonException jacksonException){
            throw JacksonInteractionException.convertException(jacksonException);
        }
        return jsonRace;
    }

    public String findAllRaces(){
        List<Race> raceList = new ArrayList<>();
        String jsonRaceArray;
        repository.findAll().forEach(raceList::add);
        try {
            jsonRaceArray = objectMapper.writeValueAsString(raceList);
        } catch (JsonProcessingException jsonProcessingException) {
            throw JacksonInteractionException.convertException(jsonProcessingException);
        }
        return jsonRaceArray;
    }

    public String update(String jsonRace) {
        Race requestBody;
        try {
            requestBody = objectMapper.readValue(jsonRace, Race.class);
        } catch (JsonProcessingException jsonProcessingException) {
            logger.error("Error while convert from json", jsonProcessingException);
            throw JacksonInteractionException.convertException(jsonProcessingException);
        }
        Integer id = requestBody.getId();
        Race race = repository.findById(id).orElseThrow(() -> RaceNotFoundException.notFound(id));
        race.setName(requestBody.getName());
        race.setFactionList(requestBody.getFactionList());
        repository.save(race);
        updateCommands();
        return race.getName();
    }

    public void deleteById(Integer id) {
        Race race = repository.findById(id).orElseThrow(() -> RaceNotFoundException.notFound(id));
        repository.delete(race);
        updateCommands();
    }

    public void updateCommands(){
        slashCommandCreator.updateCommands();
    }
}
