package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.nix.academy.dao.ProfessorDao;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.ProfessorDto;
import ua.nix.academy.persistence.entity.Professor;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;

public class ProfessorRepositoryImpl implements Repository<Professor, ProfessorDto> {
    private static ProfessorRepositoryImpl instance;
    private final Session session;

    private ProfessorRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void create(List<ProfessorDto> professorDtoList) throws AcademyDataCreateException {
        try {
            for (ProfessorDto professorDto : professorDtoList) {
                session.persist(ProfessorDao.getInstance().create(professorDto));
            }
        } catch (RuntimeException runtimeException) {
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Professor getByCriteria(String criteria) {
        Query<Professor> query = session.createQuery("select p from Professor p where p.initials = ?1", Professor.class).
                setParameter(1, criteria);
        return query.getSingleResult();
    }

    public Professor getById(Long id) {
        Query<Professor> query = session.createQuery("select p from Professor p where p.id = ?1", Professor.class).setParameter(1, id);
        return query.getSingleResult();
    }

    public static ProfessorRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new ProfessorRepositoryImpl(session);
        }
        return instance;
    }
}
