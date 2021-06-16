package ua.nix.academy.repository.impl;

import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.nix.academy.dao.LessonDao;
import ua.nix.academy.exception.AcademyDataAccessException;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.exception.AcademyDataException;
import ua.nix.academy.persistence.dto.LessonDto;
import ua.nix.academy.persistence.entity.Lesson;
import ua.nix.academy.repository.interfaces.Repository;
import ua.nix.academy.utitlity.parsers.TimeParser;

public class LessonRepositoryImpl implements Repository<Lesson, LessonDto> {
    private final Session session;
    private static final Logger logger = LoggerFactory.getLogger(LessonRepositoryImpl.class);

    public LessonRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Lesson create(LessonDto lessonDto) throws AcademyDataException {
        try {
            logger.info("Creating Lesson entity.");
            GroupRepositoryImpl groupRepository = new GroupRepositoryImpl(session);
            Lesson lesson = LessonDao.getInstance().create(
                    TimeParser.timeDateGetFromString(lessonDto.getTime()),
                    new ThemeRepositoryImpl(session).getThemeByName(lessonDto.getTheme()),
                    groupRepository.getGroupByName(lessonDto.getGroup()));
            session.persist(lesson);
            logger.info("Lesson entity was created successfully.");
            return lesson;
        } catch (RuntimeException runtimeException) {
            logger.error("Lesson entity wasn't created.",runtimeException);
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        } catch (AcademyDataAccessException academyDataAccessException) {
            logger.error("Lesson entity wasn't created.", academyDataAccessException);
            throw new AcademyDataAccessException(academyDataAccessException.getMessage(), academyDataAccessException);
        }
    }
}
