package ua.nix.academy.repository.interfaces;

import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.AbstractDto;
import ua.nix.academy.persistence.entity.AbstractEntity;

import java.util.List;

public interface Repository<ENTITY extends AbstractEntity, DTO extends AbstractDto> {
    void create(List<DTO> dtoList) throws AcademyDataCreateException;
    ENTITY getByCriteria(String criteria);
}
