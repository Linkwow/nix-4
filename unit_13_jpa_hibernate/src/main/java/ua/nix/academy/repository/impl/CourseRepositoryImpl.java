package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import ua.nix.academy.dao.CourseDao;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.repository.interfaces.Repository;


public class CourseRepositoryImpl implements Repository<Course, CourseDto> {
    private static CourseRepositoryImpl instance;
    private Session session;

    private CourseRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void create(List<CourseDto> courseDtoList) throws AcademyDataCreateException {
        try {
            for (CourseDto courseDto : courseDtoList) {
                session.persist(CourseDao.getInstance().create(courseDto));
            }
        } catch (RuntimeException runtimeException) {
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Course getByCriteria(String criteria) {
        Query<Course> query = session.createQuery("select c from Course c where c.courseNumber = ?1", Course.class).setParameter(1, criteria);
        return query.getSingleResult();
    }

    public static CourseRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new CourseRepositoryImpl(session);
        }
        return instance;
    }
}
