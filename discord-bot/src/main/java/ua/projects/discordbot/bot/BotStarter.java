package ua.projects.discordbot.bot;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component()
public class BotStarter {

    private final DiscordApi discordApi;

    @Autowired
    public BotStarter(DiscordApi discordApi) {
        this.discordApi = discordApi;
    }

    public DiscordApi getDiscordApi() {
        return this.discordApi;
    }

    public void listenChannel() {
        discordApi.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("chat")) {
                MessageAuthor messageAuthor = event.getMessageAuthor();
                discordApi.getUserById(messageAuthor.getId()).join().sendMessage("hi, " + messageAuthor.getDisplayName() + ". Start your interaction with print \"/\"");
            }
        });
    }
}
