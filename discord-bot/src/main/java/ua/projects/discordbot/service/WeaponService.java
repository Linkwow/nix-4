package ua.projects.discordbot.service;

import org.springframework.stereotype.Service;
import ua.projects.discordbot.exceptions.EntityNotFoundException;
import ua.projects.discordbot.persistence.Weapon;
import ua.projects.discordbot.repository.CommonRepository;
import ua.projects.discordbot.repository.WeaponRepository;

import java.util.List;

@Service
public class WeaponService extends CommonService implements CommonRepository<Weapon> {

    private final WeaponRepository repository;

    public WeaponService(WeaponRepository repository) {
        this.repository = repository;
    }

    public Weapon create(String type){
        Weapon weapon = repository.save(new Weapon(type));
        updateCommands();
        return weapon;
    }

    @Override
    public List<Weapon> findAll() {
        return repository.findAll();
    }

    @Override
    public Weapon find(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> EntityNotFoundException.notFound(id));
    }

    public Weapon update(Integer id, String type){
        Weapon weapon = find(id);
        weapon.setType(type);
        repository.save(weapon);
        updateCommands();
        return weapon;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
        updateCommands();
    }
}
