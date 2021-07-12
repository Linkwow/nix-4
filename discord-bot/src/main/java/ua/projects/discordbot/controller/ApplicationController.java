package ua.projects.discordbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.projects.discordbot.persistence.Race;
import ua.projects.discordbot.service.RaceService;

@Controller
@RequestMapping("/totalWarWarhammer")
public class ApplicationController {

    private RaceService raceService;

    @Autowired
    public ApplicationController(RaceService raceService){
        this.raceService = raceService;
    }

    @GetMapping("/showAllUnits")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public String getInfo(@RequestParam(name = "race")String race, @RequestParam(name = "faction")String faction, @RequestParam(name = "unit")String unit){
        return race + " " + faction + " " + unit;
    }

    @PostMapping("/createRace")
    @ResponseStatus(HttpStatus.CREATED)
    public String createRace(@RequestParam(name = "raceName") String raceName, Model model){
        Race race = new Race(raceName);
        raceService.createRace(race);
        model.addAttribute("test", raceName);
        return "saved";
    }
}
