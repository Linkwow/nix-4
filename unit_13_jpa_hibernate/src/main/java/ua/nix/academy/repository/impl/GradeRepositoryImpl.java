package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.nix.academy.dao.impl.GradeDaoImpl;
import ua.nix.academy.persistence.dto.GradeDto;
import ua.nix.academy.persistence.entity.Grade;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;
import java.util.Scanner;

public class GradeRepositoryImpl implements Repository<Grade, GradeDto> {
    private static GradeRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private GradeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<GradeDto> gradeDtoList) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                for (GradeDto gradeDto : gradeDtoList) {
                    session.persist(GradeDaoImpl.getInstance().create(gradeDto));
                }
                session.getTransaction().commit();
            } catch (Exception sqlException) {
                session.getTransaction().rollback();
                throw new Exception(sqlException.getMessage());
            }
        }
    }

    @Override
    public Grade getByCriteria(String criteria) {
        try (Session session = sessionFactory.openSession()) {
            Query<Grade> query = session.createQuery("select g from Grade g where g.value = ?1", Grade.class).setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    @Override
    public Grade getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Grade> query = session.createQuery("select g from Grade g where g.id = ?1", Grade.class).setParameter(1, id);
            return query.getSingleResult();
        }
    }

    @Override
    public List<Grade> getAllByCriteria(String criteria) {
        try (Session session = sessionFactory.openSession()) {
            Query<Grade> query = session.createQuery("select g from Grade g where g.value = ?1", Grade.class).setParameter(1, criteria);
            return query.getResultList();
        }
    }

    @Override
    public void updateById(Long id) {
        System.out.println("Enter a new grade value, or press the enter to left old value");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if (value != null) {
            try (Session session = sessionFactory.openSession()) {
                try {
                    session.getTransaction().begin();
                    Query<Grade> query = session.createQuery("select g from Grade g where g.id = ?1", Grade.class).setParameter(1, id);
                    Grade grade = query.getSingleResult();
                    grade.setValue(value);
                    session.saveOrUpdate(grade);
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
                Query<Grade> query = session.createQuery("select g from Grade g where g.id = ?1", Grade.class).setParameter(1, id);
                Grade grade = query.getSingleResult();
                session.remove(grade);
                session.getTransaction().commit();
            } catch (Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    public static GradeRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new GradeRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
