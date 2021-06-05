package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.nix.academy.dao.impl.ProfessorDaoImpl;
import ua.nix.academy.persistence.dto.ProfessorDto;
import ua.nix.academy.persistence.entity.Professor;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;

public class ProfessorRepositoryImpl implements Repository<Professor, ProfessorDto, String> {
    private static ProfessorRepositoryImpl instance;
    private SessionFactory sessionFactory;

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
            Query<Professor> query = session.createQuery("from Professor where initials = ?1", Professor.class).
                    setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    public static ProfessorRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if(instance == null){
            instance = new ProfessorRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
