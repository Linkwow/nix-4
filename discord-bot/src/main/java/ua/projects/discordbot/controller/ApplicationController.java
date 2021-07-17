package ua.projects.discordbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.projects.discordbot.persistence.Faction;
import ua.projects.discordbot.persistence.Race;
import ua.projects.discordbot.service.FactionService;
import ua.projects.discordbot.service.RaceService;

import java.util.HashMap;
import java.util.Map;

//todo add mapping for all service
@Controller
@RequestMapping("/total-war-warhammer")
public class ApplicationController {

    private RaceService raceService;

    private FactionService factionService;

    @Autowired
    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    @Autowired
    public void setFactionService(FactionService factionService) {
        this.factionService = factionService;
    }

    //fixme this method should be rewrite
    @GetMapping("/user/showAllUnits")
    public String getInfo(@RequestParam(name = "race") String race, @RequestParam(name = "faction") String faction, @RequestParam(name = "unit") String unit) {
        return race + " " + faction + " " + unit;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/createRace")
    public ModelAndView createRace(@RequestParam(name = "raceName") String name) {
        ModelAndView modelAndView = new ModelAndView("createRace");
        Race race = raceService.create(name);
        modelAndView.addObject("id", race.getId());
        modelAndView.addObject("name", race.getName());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/createFaction")
    public ModelAndView createFaction(@RequestParam(name = "factionName") String name, @RequestParam(name = "raceName") String raceName) {
        ModelAndView modelAndView = new ModelAndView("createFaction");
        Faction faction = factionService.create(name, raceName);
        modelAndView.addObject("id", faction.getId());
        modelAndView.addObject("name", faction.getName());
        modelAndView.addObject("race", faction.getRace());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/getRaces")
    public ModelAndView getRaces() {
        Map<String, Object> allRaces = new HashMap<>();
        allRaces.put("races", raceService.findAll());
        return new ModelAndView("getAllRaces", allRaces);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/getFactions")
    public ModelAndView getFactions() {
        Map<String, Object> allFactions = new HashMap<>();
        allFactions.put("factions", factionService.findAll());
        return new ModelAndView("getAllRaces", allFactions);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/getRaceById")
    public ModelAndView getRaceById(@RequestParam(name = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("getRace");
        Race race = raceService.find(id);
        modelAndView.addObject("entity", "race");
        modelAndView.addObject("id", race.getId());
        modelAndView.addObject("name", race.getName());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/getFactionById")
    public ModelAndView getFactionById(@RequestParam(name = "id") Integer id) {
        Faction faction = factionService.find(id);
        ModelAndView modelAndView = new ModelAndView("getFaction");
        modelAndView.addObject("id", faction.getId());
        modelAndView.addObject("name", faction.getName());
        modelAndView.addObject("race", faction.getRace());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/admin/updateRaceById")
    public void updateRaceById(@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name) {
        raceService.update(id, name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/admin/updateFactions")
    public void updateFactionById(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "factionName") String factionName,
            @RequestParam(name = "raceName", required = false) String raceName) {
        factionService.update(id, factionName, raceName);
    }

    @DeleteMapping("/admin/deleteRaceById")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteRace(@RequestParam(name = "id") Integer id) {
        raceService.delete(id);
    }

    @DeleteMapping("/admin/deleteFactionById")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFaction(@RequestParam(name = "id") Integer id){
        factionService.delete(id);
    }
}
