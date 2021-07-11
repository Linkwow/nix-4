package ua.projects.discordbot.bot;

import org.javacord.api.interaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandConfigurator  {

    private BotStarter botStarter;
    //todo maybe i will read this data from some file?
    private List<SlashCommandOptionChoice> units = new ArrayList<>(
            Arrays.asList(
                    SlashCommandOptionChoice.create("Lords", "Lords"),
                    SlashCommandOptionChoice.create("Heroes", "Heroes"),
                    SlashCommandOptionChoice.create("Units", "Units")));

    private List<SlashCommandOptionChoice> factions = new ArrayList<>(
            Arrays.asList(
                    SlashCommandOptionChoice.create("Reikland", "Reikland"),
                    SlashCommandOptionChoice.create("The Golden Order", "The_Golden_Order"),
                    SlashCommandOptionChoice.create("The Huntsmarshal's Expedition", "The_Huntsmarshal's_Expedition"),
                    SlashCommandOptionChoice.create("Hexoatl,", "Hexoatl,"),
                    SlashCommandOptionChoice.create("Last Defenders", "Last_Defenders"),
                    SlashCommandOptionChoice.create("Tlaqua", "Tlaqua")));

    private List<SlashCommandOptionChoice> races = new ArrayList<>(
            Arrays.asList(
                    SlashCommandOptionChoice.create("Empire", "Empire"),
                    SlashCommandOptionChoice.create("Lizardmen", "Lizardmen")));

    private List<SlashCommandOption> options = new ArrayList<>(
            Arrays.asList(
                    SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "race", "Choose race", true, races),
                    SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "faction", "Choose faction", true, factions),
                    SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "unit", "Choose unit", true, units)));

    @Autowired
    public CommandConfigurator(BotStarter botStarter) {
        this.botStarter = botStarter;
    }
    //fixme(create a normal method and remove created command to a single action)
    public void showUnits() {
        SlashCommand.with("show", "shows information from database", options).createGlobal(botStarter.getDiscordApi()).join();

        botStarter.getDiscordApi().addSlashCommandCreateListener(event -> {
            SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
            String text1 = slashCommandInteraction.getFirstOptionStringValue().orElse("no race");
            String text2 = slashCommandInteraction.getSecondOptionStringValue().orElse("no race");
            String text3 = slashCommandInteraction.getThirdOptionStringValue().orElse("no race");
            String address = "http://localhost:8080/totalWarWarhammer/show?race="+ text1 + "&faction=" + text2 + "&unit=" + text3;

            slashCommandInteraction.createImmediateResponder()
                    .setContent(address)
                    .respond();
            //remove
            List<SlashCommand> commands = botStarter.getDiscordApi().getGlobalSlashCommands().join();
            //fixme(cretate a method for clear command memory)
            for (int i = 0; i < commands.size(); i++) {
                commands.get(i).deleteGlobal();
            }
        });
    }
}