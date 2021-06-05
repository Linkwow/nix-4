package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

import ua.nix.academy.dao.impl.CourseDaoImpl;
import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.repository.interfaces.Repository;


public class CourseRepositoryImpl implements Repository<Course, CourseDto, Integer> {
    private static CourseRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private CourseRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<CourseDto> courseDtoList) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                for (CourseDto courseDto : courseDtoList) {
                    session.persist(CourseDaoImpl.getInstance().create(courseDto));
                }
                session.getTransaction().commit();
            } catch (Exception sqlException) {
                session.getTransaction().rollback();
                throw new Exception(sqlException.getMessage());
            }
        }
    }

    @Override
    public Course getByCriteria(Integer criteria) {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> courseQuery = session.createQuery("from Course where courseNumber = ?1", Course.class)
                    .setParameter(1, criteria);
            return courseQuery.getSingleResult();
        }
    }

    public static CourseRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new CourseRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
