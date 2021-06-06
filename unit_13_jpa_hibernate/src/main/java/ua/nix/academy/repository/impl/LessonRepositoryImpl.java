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
import java.util.Scanner;

public class LessonRepositoryImpl implements Repository<Lesson, LessonDto> {
    private static LessonRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private LessonRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<LessonDto> lessonDtoList) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                for (LessonDto lessonDto : lessonDtoList) {
                    session.persist(LessonDaoImpl.getInstance().create(
                            TimeParser.timeDateGetFromString(lessonDto.getTime()),
                            ThemeRepositoryImpl.getInstance(sessionFactory).getByCriteria(lessonDto.getTheme()),
                            ProfessorRepositoryImpl.getInstance(sessionFactory).getByCriteria(lessonDto.getProfessor())));
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
        try (Session session = sessionFactory.openSession()) {
            Query<Lesson> query = session.createQuery("select l from Lesson l where l.theme = ?1", Lesson.class).setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    @Override
    public Lesson getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Lesson> query = session.createQuery("select l from Lesson l where l.id = ?1", Lesson.class).setParameter(1, id);
            return query.getSingleResult();
        }
    }

    @Override
    public List<Lesson> getAllByCriteria(String criteria) {
        try (Session session = sessionFactory.openSession()) {
            Query<Lesson> query = session.createQuery("select l from Lesson l where l.theme = ?1", Lesson.class).setParameter(1, criteria);
            return query.getResultList();
        }
    }

    @Override
    public void updateById(Long id) {
        System.out.println("Enter a new theme, or press the enter to left old value");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if(value != null){
            try(Session session = sessionFactory.openSession()){
                Query<Lesson> courseQuery = session.createQuery("update Lesson l set l.theme = ?1 where l.id = ?2", Lesson.class).
                        setParameter("1", value).setParameter(2, id);
                courseQuery.executeUpdate();
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Lesson> courseQuery = session.createQuery("delete from Lesson l where l.id = ?1", Lesson.class).setParameter(1, id);
            courseQuery.executeUpdate();
        }
    }

    public static LessonRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new LessonRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
