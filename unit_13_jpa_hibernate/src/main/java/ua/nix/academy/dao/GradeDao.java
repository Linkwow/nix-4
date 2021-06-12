package ua.nix.academy.dao;

import ua.nix.academy.persistence.dto.GradeDto;
import ua.nix.academy.persistence.entity.Grade;
import ua.nix.academy.persistence.entity.Student;
import ua.nix.academy.persistence.entity.Theme;

public class GradeDao {

    private static GradeDao instance;

    private GradeDao() {
    }

    public Grade create(GradeDto gradeDto, Theme theme, Student student) {
        return new Grade(gradeDto.getValue(),
                theme,
                student);
    }

    public static GradeDao getInstance() {
        if (instance == null) {
            instance = new GradeDao();
        }
        return instance;
    }
}
