package ua.projects.discordbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.projects.discordbot.bot.SlashCommandCreator;
import ua.projects.discordbot.exceptions.EntityNotFoundException;
import ua.projects.discordbot.persistence.Category;
import ua.projects.discordbot.persistence.Faction;
import ua.projects.discordbot.persistence.Unit;
import ua.projects.discordbot.persistence.Weapon;
import ua.projects.discordbot.repository.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
public class UnitService implements CommonRepository<Unit> {

    private UnitRepository unitRepository;

    private FactionRepository factionRepository;

    private CategoryRepository categoryRepository;

    private WeaponRepository weaponRepository;

    private SlashCommandCreator slashCommandCreator;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Autowired
    public void setSlashCommandCreator(SlashCommandCreator slashCommandCreator) {
        this.slashCommandCreator = slashCommandCreator;
    }

    @Autowired
    public void setFactionRepository(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setWeaponRepository(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    public Unit create(String name, String factionName, String unitCategory, String weaponType, Map<String, Integer> parameters) {
        Faction faction = factionRepository.findFactionByNameIs(factionName);
        Category category = categoryRepository.findCategoryByUnitCategoryIs(unitCategory);
        Weapon weapon = weaponRepository.findWeaponByTypeIs(weaponType);
        Unit unit = new Unit(name);

        unit.setFaction(faction);
        unit.setCategory(category);
        unit.setWeaponType(weapon);
        unitRepository.save(unit);
        updateCommands();
        return unit;
    }

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    @Override
    public Unit find(Integer id) {
        return unitRepository.findById(id).orElseThrow(
                () -> EntityNotFoundException.notFound(id));
    }

    public Unit update(Integer id, String name, String factionName, String unitCategory, String weaponType) {
        return null;
    }


    @Override
    public void delete(Integer id) {

    }

    @Override
    public void updateCommands() {
        slashCommandCreator.updateCommands();
    }

    //todo check this method on functionality
    private Unit setUnitParameters(Unit unit, String name, String factionName, String unitCategory, String weaponType, Map<String, Integer> parameters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method;
        Character upperCaseChar;
        StringBuilder methodName = new StringBuilder();
        for (String key : parameters.keySet()) {
            methodName.append("set" + key);
            upperCaseChar = methodName.charAt(3);
            methodName.replace(3, 4, upperCaseChar.toString().toUpperCase());
            method = unit.getClass().getMethod(methodName.toString(), Integer.class);
            method.invoke(unit, parameters.get(key));
        }
        return unit;
    }
}
