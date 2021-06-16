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
    private final Session session;
    private static final Logger logger = LoggerFactory.getLogger(CourseRepositoryImpl.class);

    public CourseRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Course create(CourseDto courseDto) throws AcademyDataCreateException {
        try {
            logger.info("Creating Course entity.");
            Course course = CourseDao.getInstance().create(courseDto);
            session.persist(course);
            logger.info("Course entity was created successfully.");
            return course;
        } catch (RuntimeException runtimeException) {
            logger.error("Course entity wasn't created.", runtimeException);
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    public Course getCourseByNumber(String courseNumber) throws AcademyDataAccessException {
        try {
            logger.info("Searching course by number.");
            Query<Course> query = session.createQuery("select c from Course c where c.courseNumber = ?1", Course.class).setParameter(1, courseNumber);
            logger.info("Course was found.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.error("Course wasn't found.", runtimeException);
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }
}
