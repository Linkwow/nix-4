package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.nix.academy.dao.impl.LessonDaoImpl;
import ua.nix.academy.persistence.dto.LessonDto;
import ua.nix.academy.persistence.entity.Lesson;
import ua.nix.academy.repository.interfaces.Repository;
import ua.nix.academy.utitlity.parsers.TimeParser;

import java.util.List;

public class LessonRepositoryImpl implements Repository<Lesson, LessonDto, String> {
    private static LessonRepositoryImpl instance;
    private SessionFactory sessionFactory;

    private LessonRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<LessonDto> lessonDtoList) throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            try {
                for (LessonDto lessonDto : lessonDtoList){
                    session.persist(LessonDaoImpl.getInstance().create(
                            TimeParser.timeDateGetFromString(lessonDto.getTime()),
                            ThemeRepositoryImpl.getInstance(sessionFactory).getByCriteria(lessonDto.getTheme())
                    ));
                }
                session.getTransaction().commit();
            } catch (Exception sqlException) {
                session.getTransaction().rollback();
                throw new Exception(sqlException.getMessage());
            }
        }
    }

    @Override
    public Lesson getByCriteria(String criteria) {
        try(Session session = sessionFactory.openSession()){
            Query<Lesson> query = session.createQuery("from Lesson where theme = ?1", Lesson.class).
                    setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    public static LessonRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if(instance == null){
            instance = new LessonRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
