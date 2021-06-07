package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.nix.academy.dao.impl.ProfessorDaoImpl;
import ua.nix.academy.persistence.dto.ProfessorDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.persistence.entity.Professor;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;
import java.util.Scanner;

public class ProfessorRepositoryImpl implements Repository<Professor, ProfessorDto> {
    private static ProfessorRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private ProfessorRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<ProfessorDto> professorDtoList) throws Exception {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                for (ProfessorDto professorDto : professorDtoList) {
                    session.persist(ProfessorDaoImpl.getInstance().create(professorDto));
                }
                session.getTransaction().commit();
            } catch (Exception sqlException) {
                session.getTransaction().rollback();
                throw new Exception(sqlException.getMessage());
            }
        }
    }

    @Override
    public Professor getByCriteria(String criteria) {
        try(Session session = sessionFactory.openSession()){
            Query<Professor> query = session.createQuery("select p from Professor p where p.initials = ?1", Professor.class).
                    setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    @Override
    public Professor getById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Professor> query = session.createQuery("select p from Professor p where p.id = ?1", Professor.class).setParameter(1, id);
            return query.getSingleResult();
        }
    }

    @Override
    public void updateById(Long id) {
        System.out.println("Enter a new professor initials, or press the enter to left old value");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if(value != null){
            try(Session session = sessionFactory.openSession()){
                try {
                    session.getTransaction().begin();
                    Query<Professor> query = session.createQuery("select p from Professor p where p.id = ?1", Professor.class).setParameter(1, id);
                    Professor professor = query.getSingleResult();
                    professor.setInitials(value);
                    session.saveOrUpdate(professor);
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
        try(Session session = sessionFactory.openSession()){
            try {
                session.getTransaction().begin();
                Query<Professor> query = session.createQuery("delete from Professor p where p.id = ?1", Professor.class).setParameter(1, id);
                Professor professor = query.getSingleResult();
                session.remove(professor);
                session.getTransaction().commit();
            } catch (Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    public static ProfessorRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if(instance == null){
            instance = new ProfessorRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
