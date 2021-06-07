package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

import ua.nix.academy.dao.impl.CourseDaoImpl;
import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.repository.interfaces.Repository;


public class CourseRepositoryImpl implements Repository<Course, CourseDto> {
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
    public Course getByCriteria(String criteria) {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> query = session.createQuery("select c from Course c where c.courseNumber = ?1", Course.class).setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    @Override
    public Course getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Course> query = session.createQuery("select c from Course c where c.id = ?1", Course.class).setParameter(1, id);
            return query.getSingleResult();
        }
    }

    @Override
    public void updateById(Long id) {
        System.out.println("Enter a new course number, or press the enter to left old value");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if (value != null) {
            try (Session session = sessionFactory.openSession()) {
                try {
                    session.getTransaction().begin();
                    Query<Course> query = session.createQuery("select c from Course c where c.id = ?1", Course.class). setParameter(1, id);
                    Course course = query.getSingleResult();
                    course.setCourseNumber(value);
                    session.saveOrUpdate(course);
                    session.getTransaction().commit();
                } catch (Exception e) {
                    session.getTransaction().rollback();
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.getTransaction().begin();
                Query<Course> query = session.createQuery("select c from Course c where c.id = ?1", Course.class).setParameter(1, id);
                Course course = query.getSingleResult();
                session.remove(course);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    public static CourseRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new CourseRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
