package ua.projects.discordbot.bot;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Messenger {

    private final DiscordApi discordApi;

    private final SlashCommandCreator slashCommandCreator;

    @Autowired
    public Messenger(DiscordApi discordApi, SlashCommandCreator slashCommandCreator){
        this.discordApi = discordApi;
        this.slashCommandCreator = slashCommandCreator;
        start();
    }

    public void start(){
        slashCommandCreator.updateCommands();
        listenMainChannel();
        showUnits();
    }

    public void listenMainChannel() {
        discordApi.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("chat")) {
                MessageAuthor messageAuthor = event.getMessageAuthor();
                discordApi.getUserById(messageAuthor.getId()).join().sendMessage("hi, " +
                        messageAuthor.getDisplayName() + ". Start your interaction with print \"/\"");
            }
        });
    }

    public void showUnits() {
        discordApi.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
            String race = slashCommandInteraction.getFirstOptionStringValue().orElseThrow();
            String faction = slashCommandInteraction.getSecondOptionStringValue().orElseThrow();
            String unitType = slashCommandInteraction.getThirdOptionStringValue().orElseThrow();
            //fixme : need to understand what i will do with this link (rest?How i send parameters?)
            String address = "http://localhost:8080/total-war-warhammer/units?race=" + race + "&faction=" + faction + "&unit=" + unitType;
            slashCommandInteraction.createImmediateResponder().setContent(address).respond();
        });
    }
}
