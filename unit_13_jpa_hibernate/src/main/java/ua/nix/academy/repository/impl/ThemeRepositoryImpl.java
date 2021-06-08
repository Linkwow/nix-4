package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.academy.dao.ThemeDao;
import ua.nix.academy.exception.AcademyDataAccessException;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.ThemeDto;
import ua.nix.academy.persistence.entity.Theme;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;

public class ThemeRepositoryImpl implements Repository<Theme, ThemeDto> {
    private static ThemeRepositoryImpl instance;
    private final Session session;
    private final Logger logger;

    private ThemeRepositoryImpl(Session session) {
        this.session = session;
        logger = LoggerFactory.getLogger(ThemeRepositoryImpl.class);
    }

    @Override
    public void create(List<ThemeDto> themeDtoList) throws AcademyDataCreateException {
        try {
            logger.info("Start creating Theme entity.");
            for (ThemeDto themeDto : themeDtoList) {
                session.persist(ThemeDao.getInstance().create(themeDto));
            }
            logger.info("Create was successful.");
        } catch (RuntimeException runtimeException) {
            logger.info("Error while created.");
            throw new RuntimeException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Theme getByCriteria(String criteria) throws AcademyDataAccessException {
        try {
            Query<Theme> query = session.createQuery("select t from Theme t where t.name = ?1", Theme.class).setParameter(1, criteria);
            logger.info("Entity was taken successful.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.info("Entity was taken unsuccessful.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public static ThemeRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new ThemeRepositoryImpl(session);
        }
        return instance;
    }
}
