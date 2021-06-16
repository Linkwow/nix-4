package ua.nix.academy.repository.interfaces;

import ua.nix.academy.exception.AcademyDataException;
import ua.nix.academy.persistence.dto.AbstractDto;
import ua.nix.academy.persistence.entity.AbstractEntity;

public interface Repository<ENTITY extends AbstractEntity, DTO extends AbstractDto> {
    ENTITY create(DTO dtoList) throws AcademyDataException;
}
