package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.nix.academy.dao.ThemeDao;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.ThemeDto;
import ua.nix.academy.persistence.entity.Theme;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;

public class ThemeRepositoryImpl implements Repository<Theme, ThemeDto> {
    private static ThemeRepositoryImpl instance;
    private final Session session;

    private ThemeRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void create(List<ThemeDto> themeDtoList) throws AcademyDataCreateException {
        try {
            for (ThemeDto themeDto : themeDtoList) {
                session.persist(ThemeDao.getInstance().create(themeDto));
            }
        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Theme getByCriteria(String criteria) {
        Query<Theme> query = session.createQuery("select t from Theme t where t.name = ?1", Theme.class).setParameter(1, criteria);
        return query.getSingleResult();
    }

    public static ThemeRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new ThemeRepositoryImpl(session);
        }
        return instance;
    }
}
