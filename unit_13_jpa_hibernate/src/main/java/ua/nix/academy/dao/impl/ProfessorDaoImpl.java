package ua.nix.academy.dao.impl;

import ua.nix.academy.dao.interfaces.ProfessorDao;
import ua.nix.academy.persistence.dto.ProfessorDto;
import ua.nix.academy.persistence.entity.Professor;

public class ProfessorDaoImpl implements ProfessorDao<Professor, ProfessorDto> {

    private static ProfessorDaoImpl instance;

    private ProfessorDaoImpl(){}

    @Override
    public Professor create(ProfessorDto professorDto) {
        return new Professor(professorDto.getInitials());
    }

    public static ProfessorDaoImpl getInstance() {
        if(instance == null){
            instance = new ProfessorDaoImpl();
        }
        return instance;
    }
}
