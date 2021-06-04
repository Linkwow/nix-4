package ua.nix.academy.repository.impl;

import org.hibernate.Session;

import java.util.List;

import ua.nix.academy.dao.impl.CourseDao;
import ua.nix.academy.excepton.MyCustomSQLException;
import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.repository.SessionFactoryInitializer;
import ua.nix.academy.repository.Repository;

public class CourseRepository implements Repository<Course, CourseDto> {
    private static CourseRepository instance;
    private final SessionFactoryInitializer abstractRepository;
    private final CourseDao courseDao;

    private CourseRepository() {
        abstractRepository = SessionFactoryInitializer.getInstance();
        courseDao = CourseDao.getInstance();
    }

    @Override
    public void create(List<CourseDto> courseDtos) throws MyCustomSQLException {
        abstractRepository.sessionFactoryOpen();
        try (Session session = abstractRepository.openSession()) {
            session.getTransaction().begin();
            try {
                for (CourseDto courseDto : courseDtos) {
                    session.persist(courseDao.create(courseDto));
                }
                session.getTransaction().commit();
            } catch (Exception sqlException) {
                session.getTransaction().rollback();
                throw new MyCustomSQLException(sqlException.getMessage());
            }
        }
    }

    public static CourseRepository getInstance() {
        if (instance == null) {
            instance = new CourseRepository();
        }
        return instance;
    }
}
