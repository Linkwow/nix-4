package ua.nix.academy.dao;

import ua.nix.academy.persistence.dto.AbstractDto;
import ua.nix.academy.persistence.entity.AbstractEntity;

public interface CommonDao<ENTITY extends AbstractEntity, DTO extends AbstractDto> {

    ENTITY create(DTO dto);

}
