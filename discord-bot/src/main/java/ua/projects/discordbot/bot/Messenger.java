package ua.projects.discordbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Messenger {

    private CommandConfigurator commandConfigurator;
    private BotStarter botStarter;

    @Autowired
    public Messenger(CommandConfigurator commandConfigurator, BotStarter botStarter){
        this.commandConfigurator = commandConfigurator;
        this.botStarter = botStarter;
        start();
    }

    public void start(){
        botStarter.listenChannel();
        commandConfigurator.showUnits();
    }
}
