package ua.nix.academy.service;

import ua.nix.academy.persistence.dto.AbstractDto;

import java.util.List;

public interface Service<T extends AbstractDto> {

    void create(List<T> dtoList) throws Exception;
}
