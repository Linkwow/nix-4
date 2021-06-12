package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.academy.dao.ProfessorDao;
import ua.nix.academy.exception.AcademyDataAccessException;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.ProfessorDto;
import ua.nix.academy.persistence.entity.Professor;
import ua.nix.academy.repository.interfaces.Repository;

public class ProfessorRepositoryImpl implements Repository<Professor, ProfessorDto> {
    private static ProfessorRepositoryImpl instance;
    private final Session session;
    private final Logger logger;

    private ProfessorRepositoryImpl(Session session) {
        this.session = session;
        logger = LoggerFactory.getLogger(ProfessorRepositoryImpl.class);
    }

    @Override
    public Professor create(ProfessorDto professorDto) throws AcademyDataCreateException {
        try {
            logger.info("Start creating Professor entity.");
            Professor professor = ProfessorDao.getInstance().create(professorDto);
            session.persist(professor);
            logger.info("Create was successful.");
            return professor;
        } catch (RuntimeException runtimeException) {
            logger.info("Error while created.");
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Professor getByCriteria(String criteria) throws AcademyDataAccessException {
        try {
            Query<Professor> query = session.createQuery("select p from Professor p where p.initials = ?1", Professor.class).
                    setParameter(1, criteria);
            logger.info("Entity was taken successful.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.info("Entity was taken unsuccessful.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public static ProfessorRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new ProfessorRepositoryImpl(session);
        }
        return instance;
    }
}
