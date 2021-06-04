package ua.nix.academy.repository;

import ua.nix.academy.excepton.MyCustomSQLException;
import ua.nix.academy.persistence.dto.AbstractDto;
import ua.nix.academy.persistence.entity.AbstractEntity;

import java.util.List;

public interface Repository<ENTITY extends AbstractEntity, DTO extends AbstractDto> {
    void create(List<DTO> dtoList) throws MyCustomSQLException;
}
