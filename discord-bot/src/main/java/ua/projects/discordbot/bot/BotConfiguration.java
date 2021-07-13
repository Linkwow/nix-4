package ua.projects.discordbot.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public SlashCommandCreator initializeSlashCommandCreator(DiscordApi discordApi, SlashCommandInitializer slashCommandInitializer, DataTaker dataTaker){
        return new SlashCommandCreator(discordApi, slashCommandInitializer,dataTaker);
    }

    @Bean
    public SlashCommandInitializer initializeSlashCommandInitializer(){
        return new SlashCommandInitializer();
    }

    @Bean
    public DataTaker initializeDataTaker(){
        return new DataTaker();
    }

    @Bean
    public ObjectMapper initializeObjectMapper(){
        return new ObjectMapper();
    }
}
