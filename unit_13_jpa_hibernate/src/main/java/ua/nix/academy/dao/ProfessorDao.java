package ua.nix.academy.dao;

import ua.nix.academy.persistence.dto.ProfessorDto;
import ua.nix.academy.persistence.entity.Professor;

public class ProfessorDao {

    private static ProfessorDao instance;

    private ProfessorDao(){}

    public Professor create(ProfessorDto professorDto) {
        return new Professor(professorDto.getInitials());
    }

    public static ProfessorDao getInstance() {
        if(instance == null){
            instance = new ProfessorDao();
        }
        return instance;
    }
}
