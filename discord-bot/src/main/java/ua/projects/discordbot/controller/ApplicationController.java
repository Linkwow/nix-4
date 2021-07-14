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

    @PostMapping("/admin/createRace")
    @ResponseStatus(HttpStatus.CREATED)
    //fixme
    @ResponseBody()
    public String createRace(@RequestParam(name = "raceName") String raceName, Model model){
        Race race = new Race(raceName);
        raceService.createRace(race);
        model.addAttribute("name", raceName);
        return "saved";
    }
    //fixme rest template
    @GetMapping("/user/showAllUnits")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public String getInfo(@RequestParam(name = "race")String race, @RequestParam(name = "faction")String faction, @RequestParam(name = "unit")String unit){
        return race + " " + faction + " " + unit;
    }

    @GetMapping("/user/showRaces")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public String getRaces(){
        return raceService.findAllRaces();
    }

    @GetMapping("/user/showRaceById")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody()
    public String getRaceById(@RequestParam(name = "raceId")Integer id){
       raceService.findRaceById(id);
       return "!";
    }

    @PutMapping("/admin/updateRace")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String updateRace(@RequestParam(name = "jsonRace") String jsonRace, Model model){
        String raceName = raceService.update(jsonRace);
        model.addAttribute("name", raceName);
        return "saved";
    }

    @DeleteMapping("/admin/deleteRace")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteRace(@RequestParam(name = "id") Integer id){
        raceService.deleteById(id);
        return "delete";
    }
}
