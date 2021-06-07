package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.nix.academy.dao.impl.ThemeDaoImpl;
import ua.nix.academy.persistence.dto.ThemeDto;
import ua.nix.academy.persistence.entity.Theme;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;
import java.util.Scanner;

public class ThemeRepositoryImpl implements Repository<Theme, ThemeDto> {
    private static ThemeRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private ThemeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<ThemeDto> themeDtoList) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                for (ThemeDto themeDto : themeDtoList) {
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
        try (Session session = sessionFactory.openSession()) {
            Query<Theme> query = session.createQuery("select t from Theme t where t.name = ?1", Theme.class).
                    setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    @Override
    public Theme getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Theme> query = session.createQuery("select t from Theme t where t.id = ?1", Theme.class).
                    setParameter(1, id);
            return query.getSingleResult();
        }
    }

    @Override
    public void updateById(Long id) {
        System.out.println("Enter a new theme name, or press the enter to left old value");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if (value != null) {
            try (Session session = sessionFactory.openSession()) {
                try {
                    session.getTransaction().begin();
                    Query<Theme> query = session.createQuery("select t from Theme t where t.id = ?1", Theme.class).setParameter(1, id);
                    Theme theme = query.getSingleResult();
                    theme.setName(value);
                    session.saveOrUpdate(theme);
                    session.getTransaction().commit();
                } catch (Exception e) {
                    session.getTransaction().rollback();
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.getTransaction().begin();
                Query<Theme> query = session.createQuery("select t from Theme t where t.id = ?1", Theme.class).setParameter(1, id);
                Theme theme = query.getSingleResult();
                session.remove(theme);
                session.getTransaction().commit();
            } catch (Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    public static ThemeRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new ThemeRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
