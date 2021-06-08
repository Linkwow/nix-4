package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.nix.academy.dao.LessonDao;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.LessonDto;
import ua.nix.academy.persistence.entity.Lesson;
import ua.nix.academy.repository.interfaces.Repository;
import ua.nix.academy.utitlity.parsers.TimeParser;

import java.util.List;

public class LessonRepositoryImpl implements Repository<Lesson, LessonDto> {
    private static LessonRepositoryImpl instance;
    private final Session session;

    private LessonRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void create(List<LessonDto> lessonDtoList) throws AcademyDataCreateException {
        try {
            for (LessonDto lessonDto : lessonDtoList) {
                session.persist(LessonDao.getInstance().create(
                        TimeParser.timeDateGetFromString(lessonDto.getTime()),
                        ThemeRepositoryImpl.getInstance(session).getByCriteria(lessonDto.getTheme()),
                                GroupRepositoryImpl.getInstance(session).getByCriteria(lessonDto.getGroup())));
            }
        } catch (RuntimeException runtimeException) {
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Lesson getByCriteria(String criteria) {
        Query<Lesson> query = session.createQuery("select l from Lesson l where l.theme = ?1", Lesson.class).setParameter(1, criteria);
        return query.getSingleResult();
    }

    public static LessonRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new LessonRepositoryImpl(session);
        }
        return instance;
    }
}
