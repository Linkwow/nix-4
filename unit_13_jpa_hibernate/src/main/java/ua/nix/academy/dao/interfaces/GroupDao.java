package ua.nix.academy.dao.interfaces;

import ua.nix.academy.persistence.dto.AbstractDto;
import ua.nix.academy.persistence.dto.GroupDto;
import ua.nix.academy.persistence.entity.AbstractEntity;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.persistence.entity.Professor;

public interface GroupDao<ENTITY extends AbstractEntity, DTO extends AbstractDto> {

    ENTITY create(GroupDto groupDto, Course course, Professor professor);

}
