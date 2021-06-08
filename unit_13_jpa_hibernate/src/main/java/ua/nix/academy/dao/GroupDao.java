package ua.nix.academy.dao;

import ua.nix.academy.persistence.dto.GroupDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Professor;

public class GroupDao {
    private static GroupDao instance;

    private GroupDao(){}

    public Group create(GroupDto dto, Course course, Professor professor) {
        return new Group(
                dto.getName(),
                course,
                professor
        );
    }

    public static GroupDao getInstance() {
        if(instance == null){
            instance = new GroupDao();
        }
        return instance;
    }
}
