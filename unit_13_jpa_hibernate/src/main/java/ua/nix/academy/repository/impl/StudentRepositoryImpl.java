package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.academy.dao.StudentDao;
import ua.nix.academy.exception.AcademyDataAccessException;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.exception.AcademyDataException;
import ua.nix.academy.persistence.dto.StudentDto;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Lesson;
import ua.nix.academy.persistence.entity.Student;
import ua.nix.academy.repository.interfaces.Repository;

import java.time.ZonedDateTime;
import java.util.List;

public class StudentRepositoryImpl implements Repository<Student, StudentDto> {
    private static StudentRepositoryImpl instance;
    private final Session session;
    private final Logger logger;

    private StudentRepositoryImpl(Session session) {
        this.session = session;
        logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);
    }

    @Override
    public Student create(StudentDto studentDto) throws AcademyDataException {
        try {
            logger.info("Start creating Student entity.");
            Group group = GroupRepositoryImpl.getInstance(session).getByCriteria(studentDto.getGroupName());
            Student student = StudentDao.getInstance().create(studentDto, group);
            session.persist(student);
            logger.info("Create was successful.");
            return student;
        } catch (RuntimeException runtimeException) {
            logger.info("Error while created.");
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        } catch (AcademyDataAccessException academyDataAccessException) {
            logger.info("Error while created.");
            throw new AcademyDataAccessException(academyDataAccessException.getMessage(), academyDataAccessException);
        }
    }

    @Override
    public Student getByCriteria(String criteria) throws AcademyDataAccessException {
        try {
            Query<Student> query = session.createQuery("select s from Student s where s.initials = ?1", Student.class).setParameter(1, criteria);
            logger.info("Entity was taken successful.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.info("Entity was taken unsuccessful.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public Lesson takeInfoAboutLesson(Long id) throws AcademyDataAccessException {
        try {
            logger.info("Perform query");
            ZonedDateTime currentTime = ZonedDateTime.now();
            Group group = session.createQuery("select g from Group g join Student s on s.group.id = g.id and s.id = ?1", Group.class).
                    setParameter(1, id).getSingleResult();
            Query<Lesson> lessons = session.createQuery("select l from Lesson l join Group g on l.group.id = g.id and g.id = ?1 " +
                    "where l.zonedDateTime > ?2 order by l.zonedDateTime desc ", Lesson.class).
                    setParameter(1, group.getId()).setParameter(2, currentTime);
            List<Lesson> lessonList = lessons.getResultList();
            logger.info("Entity was taken successful.");
            return lessonList.get(0);
        } catch (RuntimeException runtimeException) {
            logger.info("Entity was taken unsuccessful.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public static StudentRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new StudentRepositoryImpl(session);
        }
        return instance;
    }
}