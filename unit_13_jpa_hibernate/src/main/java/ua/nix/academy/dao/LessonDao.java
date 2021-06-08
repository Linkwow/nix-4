package ua.nix.academy.dao;

import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Lesson;
import ua.nix.academy.persistence.entity.Theme;


import java.time.ZonedDateTime;

public class LessonDao {
    private static LessonDao instance;

    public Lesson create(ZonedDateTime zonedDateTime, Theme theme, Group group) {
        return new Lesson(
                zonedDateTime,
                theme,
                group);
    }

    public static LessonDao getInstance() {
        if(instance == null){
            instance = new LessonDao();
        }
        return instance;
    }
}
