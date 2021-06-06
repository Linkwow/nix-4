package ua.nix.academy.dao.impl;

import ua.nix.academy.dao.interfaces.GradeDao;
import ua.nix.academy.persistence.dto.GradeDto;
import ua.nix.academy.persistence.entity.Grade;

public class GradeDaoImpl implements GradeDao<Grade, GradeDto> {

    private static GradeDaoImpl instance;

    private GradeDaoImpl(){}

    @Override
    public Grade create(GradeDto gradeDto) {
        return new Grade(gradeDto.getValue());
    }

    public static GradeDaoImpl getInstance() {
        if(instance == null){
            instance = new GradeDaoImpl();
        }
        return instance;
    }
}
