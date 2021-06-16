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

public class ThemeRepositoryImpl implements Repository<Theme, ThemeDto> {
    private final Session session;
    private static final Logger logger = LoggerFactory.getLogger(ThemeRepositoryImpl.class);

    public ThemeRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Theme create(ThemeDto themeDto) throws AcademyDataCreateException {
        try {
            logger.info("Creating Theme entity.");
            Theme theme = ThemeDao.getInstance().create(themeDto);
            session.persist(theme);
            logger.info("Theme entity was created successfully.");
            return theme;
        } catch (RuntimeException runtimeException) {
            logger.error("Theme entity wasn't created.", runtimeException);
            throw new RuntimeException(runtimeException.getMessage(), runtimeException);
        }
    }

    public Theme getThemeByName(String themeName) throws AcademyDataAccessException {
        try {
            logger.info("Searching Theme by name");
            Query<Theme> query = session.createQuery("select t from Theme t where t.name = ?1", Theme.class).setParameter(1, themeName);
            logger.info("Theme was found.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.error("Theme wasn't found.", runtimeException);
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }
}
