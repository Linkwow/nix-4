package ua.projects.discordbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/totalWarWarhammer")
public class DataTaker {

    @GetMapping("/show")
    @ResponseBody
    public String getInfo(@RequestParam(name = "race")String race, @RequestParam(name = "faction")String faction, @RequestParam(name = "unit")String unit){
        return race + " " + faction + " " + unit;
    }
}
