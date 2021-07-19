package ua.projects.discordbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.server.ResponseStatusException;
import ua.projects.discordbot.exceptions.EntityNotFoundException;
import ua.projects.discordbot.exceptions.ValidationException;
import ua.projects.discordbot.persistence.Attribute;
import ua.projects.discordbot.repository.AttributeRepository;
import ua.projects.discordbot.repository.CommonRepository;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;


@Service
public class AttributeService extends CommonService implements CommonRepository<Attribute> {

    private static final Logger logger = LoggerFactory.getLogger(AttributeService.class);

    private final AttributeRepository repository;

    public AttributeService(AttributeRepository repository) {
        this.repository = repository;
    }

    public Attribute create(String description) throws ValidationException {
        try {
            if (repository.existsByDescription(description)) {
                throw new ValidationException("Description " + description + " presents in dataBase. Description should be unique.");
            } else {
                try {
                    Attribute attribute = repository.save(new Attribute(description));
                    updateCommands();
                    logger.debug("Attribute was created successfully");
                    return attribute;
                } catch (TransactionSystemException constraintViolationException) {
                    throw new ValidationException("Description is mandatory.");
                }
            }
        } catch (ValidationException validationException){
            throw new ValidationException(validationException.getMessage());
        }
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

    public Attribute update(Integer id, String description) {
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
}
