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
    private final Session session;
    private static final Logger  logger = LoggerFactory.getLogger(ProfessorRepositoryImpl.class);

    public ProfessorRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Professor create(ProfessorDto professorDto) throws AcademyDataCreateException {
        try {
            logger.info("Creating Professor entity.");
            Professor professor = ProfessorDao.getInstance().create(professorDto);
            session.persist(professor);
            logger.info("Professor entity was created successfully.");
            return professor;
        } catch (RuntimeException runtimeException) {
            logger.error("Professor entity wasn't created.", runtimeException);
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    public Professor getProfessorByInitials(String studentInitials) throws AcademyDataAccessException {
        try {
            logger.info("Searching Professor by initials.");
            Query<Professor> query = session.createQuery("select p from Professor p where p.initials = ?1", Professor.class).setParameter(1, studentInitials);
            logger.info("Professor was found.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.info("Professor wasn't found.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }
}
