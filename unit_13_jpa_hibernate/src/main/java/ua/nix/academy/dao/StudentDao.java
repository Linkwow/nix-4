package ua.nix.academy.dao;

import ua.nix.academy.persistence.dto.StudentDto;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Student;

public class StudentDao {
    private static StudentDao instance;

    private StudentDao(){}

    public Student create(StudentDto studentDto, Group group) {
        return new Student(
                studentDto.getInitials(),
                group);
    }

    public static StudentDao getInstance(){
        if(instance == null){
            instance = new StudentDao();
        }
        return instance;
    }
}
