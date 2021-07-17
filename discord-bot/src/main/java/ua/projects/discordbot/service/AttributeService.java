package ua.projects.discordbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.projects.discordbot.bot.SlashCommandCreator;
import ua.projects.discordbot.exceptions.EntityNotFoundException;
import ua.projects.discordbot.persistence.Attribute;
import ua.projects.discordbot.repository.AttributeRepository;
import ua.projects.discordbot.repository.CommonRepository;

import java.util.List;

@Service
public class AttributeService implements CommonRepository<Attribute> {

    private final AttributeRepository repository;

    private SlashCommandCreator slashCommandCreator;

    public AttributeService(AttributeRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setSlashCommandCreator(SlashCommandCreator slashCommandCreator) {
        this.slashCommandCreator = slashCommandCreator;
    }

    public Attribute create(String description){
        Attribute attribute = repository.save(new Attribute(description));
        updateCommands();
        return attribute;
    }

    @Override
    public List<Attribute> findAll() {
        return repository.findAll();
    }

    @Override
    public Attribute find(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> EntityNotFoundException.notFound(id));
    }

    public Attribute update(Integer id, String description){
        Attribute attribute = find(id);
        attribute.setDescription(description);
        repository.save(attribute);
        updateCommands();
        return attribute;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
        updateCommands();
    }

    @Override
    public void updateCommands(){
        slashCommandCreator.updateCommands();
    }
}
