package ua.projects.discordbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.projects.discordbot.persistence.Race;
import ua.projects.discordbot.service.RaceService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/total-war-warhammer")
public class ApplicationController {

    private final RaceService raceService;

    @Autowired
    public ApplicationController(RaceService raceService) {
        this.raceService = raceService;
    }

    //fixme this method should be rewrite
    @GetMapping("/user/showAllUnits")
    public String getInfo(@RequestParam(name = "race") String race, @RequestParam(name = "faction") String faction, @RequestParam(name = "unit") String unit) {
        return race + " " + faction + " " + unit;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/createRace")
    public ModelAndView createRace(@RequestParam(name = "raceName") String name) {
        ModelAndView modelAndView = new ModelAndView("create");
        Race race = raceService.createRace(name);
        modelAndView.addObject("entity", "Race");
        modelAndView.addObject("id", race.getId());
        modelAndView.addObject("name", race.getName());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/getRaces")
    public ModelAndView getRaces() {
        Map<String, Object> allRaces = new HashMap<>();
        allRaces.put("races", raceService.findAllRaces());
        return new ModelAndView("getAll", allRaces).addObject("entity", "races");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/getRaceById")
    public ModelAndView getRaceById(@RequestParam(name = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("race");
        Race race = raceService.findRaceById(id);
        modelAndView.addObject("entity", "race");
        modelAndView.addObject("id", race.getId());
        modelAndView.addObject("name", race.getName());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/admin/updateRaceById")
    public ModelAndView updateRace(@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("update");
        Race race = raceService.updateByID(id, name);
        modelAndView.addObject("entity", "race");
        modelAndView.addObject("id", race.getId());
        modelAndView.addObject("name", race.getName());
        return modelAndView;
    }

    @DeleteMapping("/admin/deleteRaceById")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView deleteRace(@RequestParam(name = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        Race race = raceService.findRaceById(id);
        modelAndView.addObject("entity", "race");
        modelAndView.addObject("id", race.getId());
        modelAndView.addObject("name", race.getName());
        raceService.deleteById(id);
        return modelAndView;
    }
}
