package ua.nix.academy.repository.interfaces;

import ua.nix.academy.persistence.dto.AbstractDto;
import ua.nix.academy.persistence.entity.AbstractEntity;

import java.util.List;

public interface Repository<ENTITY extends AbstractEntity, DTO extends AbstractDto> {
    void create(List<DTO> dtoList) throws Exception;
    ENTITY getByCriteria(String criteria);
    ENTITY getById(Long id);
    void updateById(Long id);
    void deleteById(Long id);
}
