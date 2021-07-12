package ua.projects.discordbot.bot;

import org.javacord.api.DiscordApi;
import org.javacord.api.interaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SlashCommandCreator {

    private final DiscordApi discordApi;

    private final SlashCommandInitializer slashCommandInitializer;

    private final DataTaker dataTaker;

    private SlashCommand slashCommand;

    private List<SlashCommandOptionChoice> races = new ArrayList<>();

    private List<SlashCommandOptionChoice> factions = new ArrayList<>();

    private final List<SlashCommandOptionChoice> units = new ArrayList<>(
            Arrays.asList(
                    SlashCommandOptionChoice.create("Lords", "Lords"),
                    SlashCommandOptionChoice.create("Heroes", "Heroes"),
                    SlashCommandOptionChoice.create("Units", "Units")));

    private final List<SlashCommandOption> entities = new ArrayList<>(
            Arrays.asList(
                    SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "race", "Choose race", true, getRaces()),
                    SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "faction", "Choose faction", true, getFactions()),
                    SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "unit", "Choose unit", true, getUnits())));

    public void setFactions(List<String> inputData) {
        factions = slashCommandInitializer.createCommandsOptions(inputData);
    }

    public void setRaces(List<String> inputData) {
       races = slashCommandInitializer.createCommandsOptions(inputData);
    }

    public void setCommandList() {
        slashCommand = SlashCommand.with("show-units", "shows units from chosen race and faction", getEntities()).createGlobal(discordApi).join();
    }

    public List<SlashCommandOptionChoice> getRaces() {
        return races;
    }

    public List<SlashCommandOptionChoice> getFactions() {
        return factions;
    }

    public List<SlashCommandOptionChoice> getUnits() {
        return units;
    }

    public List<SlashCommandOption> getEntities() {
        return entities;
    }

    @Autowired
    public SlashCommandCreator(DiscordApi discordApi, SlashCommandInitializer slashCommandInitializer, DataTaker dataTaker) {
        this.discordApi = discordApi;
        this.slashCommandInitializer = slashCommandInitializer;
        this.dataTaker = dataTaker;
    }

    public void updateCommands() {
        deleteCommand();
        setRaces(dataTaker.getRacesFromDataBase());
        setFactions(dataTaker.getFactionsFromDataBase());
        setCommandList();
        SlashCommand updatedCommand = SlashCommand.with("show-units", "shows units from chosen race and faction", getEntities()).createGlobal(discordApi).join();
    }

    public void deleteCommand() {
        for (SlashCommand command : discordApi.getGlobalSlashCommands().join()) {
            if(command.getName().equals("show-units"))
                command.deleteGlobal();
        }
    }
}