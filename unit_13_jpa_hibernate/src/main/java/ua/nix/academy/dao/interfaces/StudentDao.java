package ua.nix.academy.dao.interfaces;

import ua.nix.academy.persistence.dto.AbstractDto;
import ua.nix.academy.persistence.entity.AbstractEntity;
import ua.nix.academy.persistence.entity.Group;

public interface StudentDao  <ENTITY extends AbstractEntity, DTO extends AbstractDto> {

    ENTITY create(DTO studentDto, Group group);
}
