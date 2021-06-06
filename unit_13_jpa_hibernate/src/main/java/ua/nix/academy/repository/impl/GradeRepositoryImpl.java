package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.nix.academy.dao.impl.GradeDaoImpl;
import ua.nix.academy.persistence.dto.GradeDto;
import ua.nix.academy.persistence.entity.Grade;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;

public class GradeRepositoryImpl implements Repository<Grade, GradeDto, Integer> {
    private static GradeRepositoryImpl instance;
    private SessionFactory sessionFactory;

    private GradeRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<GradeDto> gradeDtoList) throws Exception {
        try(Session session = sessionFactory.openSession()){
            try {
                session.getTransaction().begin();
                for(GradeDto gradeDto : gradeDtoList){
                    session.persist(GradeDaoImpl.getInstance().create(gradeDto));
                }
                session.getTransaction().commit();
            } catch (Exception sqlException) {
                session.getTransaction().rollback();
                throw new Exception(sqlException.getMessage());
            }
        }
    }

    @Override
    public Grade getByCriteria(Integer criteria) {
        try(Session session = sessionFactory.openSession()){
            Query<Grade> query = session.createQuery("from Grade where value = ?1", Grade.class).setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    public static GradeRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if(instance == null){
            instance = new GradeRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
