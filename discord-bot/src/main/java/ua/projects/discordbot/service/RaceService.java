package ua.projects.discordbot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.projects.discordbot.exceptions.NotFoundException;
import ua.projects.discordbot.exceptions.JacksonException;
import ua.projects.discordbot.bot.SlashCommandCreator;
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

    public Race createRace(Race race) {
        Race race1 = repository.save(race);
        updateCommands();
        return race1;

    }

    public String findRaceById(Integer id) {
        Race race = repository.findById(id).orElseThrow(() -> NotFoundException.notFound(id));
        String jsonRace;
        try {
            jsonRace = objectMapper.writeValueAsString(race);
        } catch (com.fasterxml.jackson.core.JacksonException jacksonException){
            throw JacksonException.convertException(jacksonException);
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
            throw JacksonException.convertException(jsonProcessingException);
        }
        return jsonRaceArray;
    }

    public String update(String jsonRace) {
        Race requestBody;
        try {
            requestBody = objectMapper.readValue(jsonRace, Race.class);
        } catch (JsonProcessingException jsonProcessingException) {
            logger.error("Error while convert from json", jsonProcessingException);
            throw JacksonException.convertException(jsonProcessingException);
        }
        Integer id = requestBody.getId();
        Race race = repository.findById(id).orElseThrow(() -> NotFoundException.notFound(id));
        race.setName(requestBody.getName());
        race.setFactionList(requestBody.getFactionList());
        repository.save(race);
        updateCommands();
        return race.getName();
    }

    public void deleteById(Integer id) {
        Race race = repository.findById(id).orElseThrow(() -> NotFoundException.notFound(id));
        repository.delete(race);
        updateCommands();
    }

    public void updateCommands(){
        slashCommandCreator.updateCommands();
    }
}
