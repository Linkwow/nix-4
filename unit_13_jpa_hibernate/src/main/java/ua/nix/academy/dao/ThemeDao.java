package ua.nix.academy.dao;

import ua.nix.academy.persistence.dto.ThemeDto;
import ua.nix.academy.persistence.entity.Theme;

public class ThemeDao {
    private static ThemeDao instance;

    private ThemeDao(){}

    public Theme create(ThemeDto themeDto) {
        return new Theme(themeDto.getName());
    }

    public static ThemeDao getInstance() {
        if(instance == null){
            instance = new ThemeDao();
        }
        return instance;
    }
}
