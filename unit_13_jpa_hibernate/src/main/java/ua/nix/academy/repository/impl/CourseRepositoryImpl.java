package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.academy.dao.CourseDao;
import ua.nix.academy.exception.AcademyDataAccessException;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.repository.interfaces.Repository;

public class CourseRepositoryImpl implements Repository<Course, CourseDto> {
    private static CourseRepositoryImpl instance;
    private final Session session;
    private final Logger logger;

    private CourseRepositoryImpl(Session session) {
        this.session = session;
        logger = LoggerFactory.getLogger(CourseRepositoryImpl.class);
    }

    @Override
    public Course create(CourseDto courseDto) throws AcademyDataCreateException {
        try {
            logger.info("Start creating Course entity.");
            Course course = CourseDao.getInstance().create(courseDto);
            session.persist(course);
            logger.info("Create was successful.");
            return course;
        } catch (RuntimeException runtimeException) {
            logger.info("Error while created.");
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Course getByCriteria(String criteria) throws AcademyDataAccessException {
        try {
            Query<Course> query = session.createQuery("select c from Course c where c.courseNumber = ?1", Course.class).setParameter(1, criteria);
            logger.info("Entity was taken successful.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.info("Entity was taken unsuccessful.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public static CourseRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new CourseRepositoryImpl(session);
        }
        return instance;
    }
}
