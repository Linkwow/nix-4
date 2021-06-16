package ua.nix.academy.repository.impl;

import org.hibernate.Session;

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
    private final Session session;
    private static final Logger logger = LoggerFactory.getLogger(GradeRepositoryImpl.class);

    public GradeRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Grade create(GradeDto gradeDto) throws AcademyDataException {
        try {
            logger.info("Creating Grade entity.");
            Grade grade = GradeDao.getInstance().create(gradeDto,
                    new ThemeRepositoryImpl(session).getThemeByName(gradeDto.getTheme()),
                    new StudentRepositoryImpl(session).getByInitials(gradeDto.getStudent()));
                session.persist(grade);
            logger.info("Grade entity was created successfully.");
            return grade;
        } catch (RuntimeException runtimeException) {
            logger.error("Grade entity wasn't created.", runtimeException);
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        } catch (AcademyDataAccessException academyDataAccessException) {
            logger.error("Grade entity wasn't created.", academyDataAccessException);
            throw new AcademyDataAccessException(academyDataAccessException.getMessage(), academyDataAccessException);
        }
    }
}
