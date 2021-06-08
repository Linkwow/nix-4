package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.nix.academy.dao.StudentDao;
import ua.nix.academy.exception.AcademyDataCreateException;
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

    private StudentRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void create(List<StudentDto> studentDtoList) throws AcademyDataCreateException {
        try {
            for (StudentDto studentDto : studentDtoList) {
                Group group = GroupRepositoryImpl.getInstance(session).getByCriteria(studentDto.getGroupName());
                session.persist(StudentDao.getInstance().create(studentDto, group));
            }
        } catch (RuntimeException runtimeException) {
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Student getByCriteria(String criteria) {
        Query<Student> query = session.createQuery("select s from Student s where s.initials = ?1", Student.class).setParameter(1, criteria);
        return query.getSingleResult();
    }

    public Student getById(Long id) {
        Query<Student> query = session.createQuery("select s from Student s where s.id = ?1", Student.class).setParameter(1, id);
        return query.getSingleResult();
    }

    public Lesson takeInfoAboutLesson(Long id) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        Group group = session.createQuery("select g from Group g join Student s on s.group.id = g.id and s.id = ?1", Group.class).
                setParameter(1, id).getSingleResult();
        Query<Lesson> lessons = session.createQuery("select l from Lesson l join Group g on l.group.id = g.id and g.id = ?1 " +
                "where l.zonedDateTime > ?2 order by l.zonedDateTime desc ", Lesson.class).
                setParameter(1,group.getId()).setParameter(2, currentTime);
        List<Lesson> lessonList = lessons.getResultList();
        return lessonList.get(0);
    }

    public static StudentRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new StudentRepositoryImpl(session);
        }
        return instance;
    }
}