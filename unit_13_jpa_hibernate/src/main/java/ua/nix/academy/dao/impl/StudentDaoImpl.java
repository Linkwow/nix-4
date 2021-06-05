package ua.nix.academy.dao.impl;

import ua.nix.academy.dao.interfaces.StudentDao;
import ua.nix.academy.persistence.dto.StudentDto;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Student;

public class StudentDaoImpl implements StudentDao<Student, StudentDto> {
    private static StudentDaoImpl instance;

    private StudentDaoImpl(){}

    @Override
    public Student create(StudentDto studentDto, Group group) {
        return new Student(
                studentDto.getInitials(),
                group);
    }

    public static StudentDaoImpl getInstance(){
        if(instance == null){
            instance = new StudentDaoImpl();
        }
        return instance;
    }
}
