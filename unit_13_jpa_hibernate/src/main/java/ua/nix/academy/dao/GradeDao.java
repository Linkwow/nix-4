package ua.nix.academy.dao;

import ua.nix.academy.persistence.dto.GradeDto;
import ua.nix.academy.persistence.entity.Grade;
import ua.nix.academy.persistence.entity.Theme;

public class GradeDao {

    private static GradeDao instance;

    private GradeDao() {
    }

    public Grade create(GradeDto gradeDto, Theme theme) {
        return new Grade(gradeDto.getValue(),
                theme);
    }

    public static GradeDao getInstance() {
        if (instance == null) {
            instance = new GradeDao();
        }
        return instance;
    }
}
