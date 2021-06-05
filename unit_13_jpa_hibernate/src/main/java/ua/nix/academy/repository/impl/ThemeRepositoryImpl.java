package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.nix.academy.dao.impl.ThemeDaoImpl;
import ua.nix.academy.persistence.dto.ThemeDto;
import ua.nix.academy.persistence.entity.Theme;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;

public class ThemeRepositoryImpl implements Repository<Theme, ThemeDto, String> {
    private static ThemeRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private ThemeRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<ThemeDto> themeDtoList) throws Exception {
        try(Session session = sessionFactory.openSession()){
            session.getTransaction().begin();
            try {
                for(ThemeDto themeDto : themeDtoList) {
                    session.persist(ThemeDaoImpl.getInstance().create(themeDto));
                }
                session.getTransaction().commit();
            } catch (Exception sqlException) {
                session.getTransaction().rollback();
                throw new Exception(sqlException.getMessage());
            }
        }
    }

    @Override
    public Theme getByCriteria(String criteria) {
        try(Session session = sessionFactory.openSession()){
            Query<Theme> query = session.createQuery("from Theme where name = ?1", Theme.class).
                    setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    public static ThemeRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if(instance == null){
            instance = new ThemeRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
