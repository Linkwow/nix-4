package ua.projects.discordbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.projects.discordbot.bot.DataTaker;
import ua.projects.discordbot.bot.SlashCommandCreator;
import ua.projects.discordbot.bot.SlashCommandInitializer;

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
