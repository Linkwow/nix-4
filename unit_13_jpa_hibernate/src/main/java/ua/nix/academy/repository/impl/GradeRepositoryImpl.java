package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.academy.dao.GradeDao;
import ua.nix.academy.exception.AcademyDataAccessException;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.exception.AcademyDataException;
import ua.nix.academy.persistence.dto.GradeDto;
import ua.nix.academy.persistence.entity.Grade;
import ua.nix.academy.repository.interfaces.Repository;

public class GradeRepositoryImpl implements Repository<Grade, GradeDto> {
    private static GradeRepositoryImpl instance;
    private final Session session;
    private final Logger logger;

    private GradeRepositoryImpl(Session session) {
        this.session = session;
        logger = LoggerFactory.getLogger(GradeRepositoryImpl.class);
    }

    @Override
    public Grade create(GradeDto gradeDto) throws AcademyDataException {
        try {
            logger.info("Start creating Grade entity.");
            Grade grade = GradeDao.getInstance().create(gradeDto,
                    ThemeRepositoryImpl.getInstance(session).getByCriteria(gradeDto.getTheme()),
                    StudentRepositoryImpl.getInstance(session).getByCriteria(gradeDto.getStudent()));
                session.persist(grade);
            logger.info("Create was successful.");
            return grade;
        } catch (RuntimeException runtimeException) {
            logger.info("Error while created.");
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        } catch (AcademyDataAccessException academyDataAccessException) {
            logger.info("Error while created.");
            throw new AcademyDataAccessException(academyDataAccessException.getMessage(), academyDataAccessException);
        }
    }

    @Override
    public Grade getByCriteria(String criteria) throws AcademyDataAccessException {
        try {
            Query<Grade> query = session.createQuery("select g from Grade g where g.value = ?1", Grade.class).setParameter(1, criteria);
            logger.info("Entity was taken successful.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.info("Entity was taken unsuccessful.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public static GradeRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new GradeRepositoryImpl(session);
        }
        return instance;
    }
}
