package ua.nix.academy.dao.impl;

import ua.nix.academy.dao.interfaces.LessonDao;
import ua.nix.academy.persistence.dto.LessonDto;
import ua.nix.academy.persistence.entity.Lesson;
import ua.nix.academy.persistence.entity.Professor;
import ua.nix.academy.persistence.entity.Theme;

import java.time.ZonedDateTime;

public class LessonDaoImpl implements LessonDao<Lesson, LessonDto> {
    private static LessonDaoImpl instance;

    @Override
    public Lesson create(ZonedDateTime zonedDateTime, Theme theme, Professor professor) {
        return new Lesson(
                zonedDateTime,
                theme,
                professor);
    }

    public static LessonDaoImpl getInstance() {
        if(instance == null){
            instance = new LessonDaoImpl();
        }
        return instance;
    }
}
