package ua.nix.academy.dao.impl;

import ua.nix.academy.dao.interfaces.GroupDao;
import ua.nix.academy.persistence.dto.GroupDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Professor;

public class GroupDaoImpl implements GroupDao<Group, GroupDto> {
    private static GroupDaoImpl instance;

    private GroupDaoImpl(){}

    @Override
    public Group create(GroupDto dto, Course course, Professor professor) {
        return new Group(
                dto.getName(),
                course,
                professor
        );
    }

    public static GroupDaoImpl getInstance() {
        if(instance == null){
            instance = new GroupDaoImpl();
        }
        return instance;
    }
}
