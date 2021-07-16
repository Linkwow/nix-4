package ua.projects.discordbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.projects.discordbot.bot.SlashCommandCreator;
import ua.projects.discordbot.persistence.Faction;
import ua.projects.discordbot.repository.FactionRepository;

@Service
public class FactionService {

    private final FactionRepository factionRepository;

    private SlashCommandCreator slashCommandCreator;

    @Autowired
    public FactionService(FactionRepository factionRepository){
        this.factionRepository = factionRepository;
    }

    @Autowired
    public void setSlashCommandCreator(SlashCommandCreator slashCommandCreator) {
        this.slashCommandCreator = slashCommandCreator;
    }

    public Faction createFaction(String name){
        Faction factionToCreate = factionRepository.save(new Faction(name));
        updateCommands();
        return factionToCreate;
    }

    private void updateCommands(){
        slashCommandCreator.updateCommands();
    }


}
