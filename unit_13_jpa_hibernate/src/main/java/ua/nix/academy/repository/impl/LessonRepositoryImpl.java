package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

import java.util.List;

public class LessonRepositoryImpl implements Repository<Lesson, LessonDto> {
    private static LessonRepositoryImpl instance;
    private final Session session;
    private final Logger logger;

    private LessonRepositoryImpl(Session session) {
        this.session = session;
        logger = LoggerFactory.getLogger(LessonRepositoryImpl.class);
    }

    @Override
    public Lesson create(LessonDto lessonDto) throws AcademyDataException {
        try {
            logger.info("Start creating Lesson entity.");
            Lesson lesson = LessonDao.getInstance().create(
                    TimeParser.timeDateGetFromString(lessonDto.getTime()),
                    ThemeRepositoryImpl.getInstance(session).getByCriteria(lessonDto.getTheme()),
                    GroupRepositoryImpl.getInstance(session).getByCriteria(lessonDto.getGroup()));
            session.persist(lesson);
            logger.info("Create was successful.");
            return lesson;
        } catch (RuntimeException runtimeException) {
            logger.info("Error while created.");
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        } catch (AcademyDataAccessException academyDataAccessException) {
            logger.info("Error while created.");
            throw new AcademyDataAccessException(academyDataAccessException.getMessage(), academyDataAccessException);
        }
    }

    @Override
    public Lesson getByCriteria(String criteria) throws AcademyDataAccessException {
        try {
            Query<Lesson> query = session.createQuery("select l from Lesson l where l.theme = ?1", Lesson.class).setParameter(1, criteria);
            logger.info("Entity was taken successful.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.info("Entity was taken unsuccessful.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public static LessonRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new LessonRepositoryImpl(session);
        }
        return instance;
    }
}
