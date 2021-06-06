package ua.nix.academy.dao.interfaces;

import ua.nix.academy.persistence.dto.AbstractDto;
import ua.nix.academy.persistence.dto.LessonDto;
import ua.nix.academy.persistence.entity.AbstractEntity;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.persistence.entity.Professor;
import ua.nix.academy.persistence.entity.Theme;

import java.time.ZonedDateTime;

public interface LessonDao <ENTITY extends AbstractEntity, DTO extends AbstractDto> {

    ENTITY create(ZonedDateTime zonedDateTime, Theme theme, Professor professor);
}
