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

public class StudentRepositoryImpl implements Repository<Student, StudentDto> {
    private final Session session;
    private static final Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);

    public StudentRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Student create(StudentDto studentDto) throws AcademyDataException {
        try {
            logger.info("Creating Student entity.");
            Group group = new GroupRepositoryImpl(session).getGroupByName(studentDto.getGroupName());
            Student student = StudentDao.getInstance().create(studentDto, group);
            session.persist(student);
            logger.info("Student entity was created successfully.");
            return student;
        } catch (RuntimeException runtimeException) {
            logger.error("Student entity wasn't created.", runtimeException);
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        } catch (AcademyDataAccessException academyDataAccessException) {
            logger.error("Student entity wasn't created.", academyDataAccessException);
            throw new AcademyDataAccessException(academyDataAccessException.getMessage(), academyDataAccessException);
        }
    }

    public Student getByInitials(String initials) throws AcademyDataAccessException {
        try {
            logger.info("Searching Student by initials.");
            Query<Student> query = session.createQuery("select s from Student s where s.initials = ?1", Student.class).setParameter(1, initials);
            logger.info("Student was found.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.error("Student wasn't found.", runtimeException);
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public Lesson takeInfoAboutLesson(Long studentId) throws AcademyDataAccessException {
        try {
            logger.info("Searching Lesson");
            ZonedDateTime currentTime = ZonedDateTime.now();
            Query<Lesson> query = session.createQuery("select l from Lesson l " +
                    "join Group g on g.id = l.group.id " +
                    "join Student s on s.group.id = g.id and s.id = ?1 where l.zonedDateTime > ?2 order by l.zonedDateTime", Lesson.class)
                    .setParameter(1, studentId)
                    .setParameter(2, currentTime).setMaxResults(1);
            Lesson lesson = query.getSingleResult();
            logger.info("Lesson was found.");
            return lesson;
        } catch (RuntimeException runtimeException) {
            logger.error("Lesson wasn't found.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }
}