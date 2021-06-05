package ua.nix.academy.dao.interfaces;

import ua.nix.academy.persistence.dto.AbstractDto;
import ua.nix.academy.persistence.entity.AbstractEntity;

public interface CourseDao <ENTITY extends AbstractEntity, DTO extends AbstractDto> {

    ENTITY create(DTO courseDto);
}
