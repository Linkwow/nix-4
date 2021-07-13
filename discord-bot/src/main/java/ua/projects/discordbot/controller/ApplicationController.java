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

    private final RaceService raceService;

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
    @ResponseBody
    public String createRace(@RequestParam(name = "raceName") String raceName, Model model){
        Race race = new Race(raceName);
        raceService.createRace(race);
        model.addAttribute("name", raceName);
        return "saved";
    }

    @GetMapping("/showRaces")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public String getRaces(){
        return raceService.findAllRaces();
    }

    @GetMapping("/showRaceById")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public String getRaceById(@RequestParam(name = "raceId")Integer id){
       return raceService.findRaceById(id);
    }

    @PutMapping("/updateRace")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateRace(@RequestParam(name = "jsonRace") String jsonRace, Model model){
        String raceName = raceService.update(jsonRace);
        model.addAttribute("name", raceName);
        return "saved";
    }

    @DeleteMapping("/deleteRace")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteRace(@RequestParam(name = "id") Integer id){
        raceService.deleteById(id);
        return "delete";
    }
}
