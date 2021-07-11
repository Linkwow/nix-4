package ua.projects.discordbot.bot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
public class BotConfiguration {

    @Value("${token}")
    private String token;

    @Bean
    public DiscordApi initializeConnect() {
        return new DiscordApiBuilder().setToken(token).login().join();
    }

    @Bean
    public BotStarter initializeBotStarter(DiscordApi discordApi){
        return new BotStarter(discordApi);
    }
}
