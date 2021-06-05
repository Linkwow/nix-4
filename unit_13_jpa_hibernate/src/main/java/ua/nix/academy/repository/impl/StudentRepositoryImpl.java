package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.nix.academy.dao.impl.StudentDaoImpl;
import ua.nix.academy.persistence.dto.StudentDto;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Student;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;

public class StudentRepositoryImpl implements Repository<Student, StudentDto, String> {
    private static StudentRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private StudentRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<StudentDto> studentDtos) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                for (StudentDto studentDto : studentDtos) {
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
        return null;
    }

    public static StudentRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if(instance == null){
            instance = new StudentRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
