package ua.projects.discordbot.service;

import org.springframework.stereotype.Service;
import ua.projects.discordbot.exceptions.EntityNotFoundException;
import ua.projects.discordbot.persistence.Category;
import ua.projects.discordbot.repository.CategoryRepository;
import ua.projects.discordbot.repository.CommonRepository;

import java.util.List;

@Service
public class CategoryService extends CommonService implements CommonRepository<Category> {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(String unitCategory){
        Category category = repository.save(new Category(unitCategory));
        updateCommands();
        return category;
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category find(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> EntityNotFoundException.notFound(id));
    }

    public Category update(Integer id, String unitCategory){
        Category category = find(id);
        category.setUnitCategory(unitCategory);
        repository.save(category);
        updateCommands();
        return category;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
        updateCommands();
    }
}
