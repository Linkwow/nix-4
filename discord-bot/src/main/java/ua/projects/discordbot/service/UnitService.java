package ua.projects.discordbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.projects.discordbot.exceptions.EntityNotFoundException;
import ua.projects.discordbot.persistence.*;
import ua.projects.discordbot.repository.*;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UnitService extends CommonService implements CommonRepository<Unit> {

    private final UnitRepository unitRepository;

    private FactionRepository factionRepository;

    private CategoryRepository categoryRepository;

    private WeaponRepository weaponRepository;

    private AttributeRepository attributeRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
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

    @Autowired
    public void setAttributeRepository(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Transactional
    public Unit create(String name, String factionName, String unitCategory, String weaponType, String attributes, Map<String, Integer> parameters) {
        Faction faction = factionRepository.findFactionByNameIs(factionName);
        Category category = categoryRepository.findCategoryByUnitCategoryIs(unitCategory);
        Weapon weapon = weaponRepository.findWeaponByTypeIs(weaponType);
        Set<Attribute> attributeSet = attributeRepository.findAllByDescriptionIn(attributes.split("&="));
        Unit unit = new Unit(name);
        unit.setFaction(faction);
        unit.setCategory(category);
        unit.setWeaponType(weapon);
        unit.setAttributeSet(attributeSet);
        try {
            setUnitParameters(unit, parameters);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException("Error while invocation parameter set");
        }
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

    @Transactional
    public Unit update(Integer id, String name, String factionName, String unitCategory, String weaponType, String attributes, Map<String, Integer> parameters) {
        Unit unit = find(id);
        unit.setName(Optional.of(name).orElse(unit.getName()));
        unit.setFaction(factionRepository.findFactionByNameIs(
                Optional.of(factionName).orElse(unit.getFaction().getName())));
        unit.setCategory(categoryRepository.findCategoryByUnitCategoryIs(
                Optional.of(unitCategory).orElse(unit.getCategory().getUnitCategory())));
        unit.setWeaponType(weaponRepository.findWeaponByTypeIs(
                Optional.of(weaponType).orElse(unit.getWeaponType().getType())));
        if(attributes != null)
            unit.setAttributeSet(attributeRepository.findAllByDescriptionIn(attributes.split("&=")));
        try {
            setUnitParameters(unit, parameters);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("Error while invocation parameter set");
        }
        updateCommands();
        return unit;
    }

    @Override
    public void delete(Integer id) {
        unitRepository.deleteById(id);
    }

    //todo check this method on functionality
    private void setUnitParameters(Unit unit, Map<String, Integer> parameters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (String methodName : parameters.keySet()) {
            if (parameters.get(methodName) != null) {
                Method method = unit.getClass().getMethod(methodName, Integer.class);
                method.invoke(unit, parameters.get(methodName));
            }
        }
        updateCommands();
    }

    public List<Unit> getUnitsByFactionAndCategory(String faction, String category){
       return unitRepository.getUnitByFactionAndCategory(faction, category);
    }
}
