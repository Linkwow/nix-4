package ua.nix.academy.dao.impl;

import ua.nix.academy.dao.interfaces.ThemeDao;
import ua.nix.academy.persistence.dto.ThemeDto;
import ua.nix.academy.persistence.entity.Theme;

public class ThemeDaoImpl implements ThemeDao<Theme, ThemeDto> {
    private static ThemeDaoImpl instance;

    private ThemeDaoImpl(){}

    @Override
    public Theme create(ThemeDto themeDto) {
        return new Theme(themeDto.getName());
    }

    public static ThemeDaoImpl getInstance() {
        if(instance == null){
            instance = new ThemeDaoImpl();
        }
        return instance;
    }
}
