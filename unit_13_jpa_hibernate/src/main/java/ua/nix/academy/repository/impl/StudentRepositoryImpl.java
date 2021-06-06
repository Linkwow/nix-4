package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.nix.academy.dao.impl.StudentDaoImpl;
import ua.nix.academy.persistence.dto.StudentDto;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Student;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;
import java.util.Scanner;

public class StudentRepositoryImpl implements Repository<Student, StudentDto> {
    private static StudentRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private StudentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<StudentDto> studentDtoList) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                for (StudentDto studentDto : studentDtoList) {
                    Group group = GroupRepositoryImpl.getInstance(sessionFactory).getByCriteria(studentDto.getGroupName());
                    session.persist(StudentDaoImpl.getInstance().create(studentDto, group));
                }
                session.getTransaction().commit();
            } catch (Exception sqlException) {
                session.getTransaction().rollback();
                throw new Exception(sqlException.getMessage());
            }
        }
    }

    @Override
    public Student getByCriteria(String criteria) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("select s from Student s where s.initials = ?1", Student.class).setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    @Override
    public Student getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("select s from Student s where s.id = ?1", Student.class).setParameter(1, id);
            return query.getSingleResult();
        }
    }

    @Override
    public List<Student> getAllByCriteria(String criteria) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> query = session.createQuery("select s from Student s where s.initials = ?1", Student.class).setParameter(1, criteria);
            return query.getResultList();
        }
    }

    @Override
    public void updateById(Long id) {
        System.out.println("Enter a new initials, or press the enter to left old value");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if (value != null) {
            try (Session session = sessionFactory.openSession()) {
                try {
                    session.getTransaction().begin();
                    Query<Student> studentQuery = session.createQuery("select s from Student s where s.id = ?1", Student.class).
                            setParameter(1, id);
                    Student student = studentQuery.getSingleResult();
                    session.evict(student);
                    student.setInitials(value);
                    session.update(student);
                    session.getTransaction().commit();
                } catch (Exception e){
                    session.getTransaction().rollback();
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Student> courseQuery = session.createQuery("delete from Student s where s.id = ?1", Student.class).setParameter(1, id);
            courseQuery.executeUpdate();
        }
    }

    public static StudentRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new StudentRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
