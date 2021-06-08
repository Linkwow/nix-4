package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.nix.academy.dao.GradeDao;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.GradeDto;
import ua.nix.academy.persistence.entity.Grade;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;

public class GradeRepositoryImpl implements Repository<Grade, GradeDto> {
    private static GradeRepositoryImpl instance;
    private Session session;

    private GradeRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void create(List<GradeDto> gradeDtoList) throws AcademyDataCreateException {
        try {
            for (GradeDto gradeDto : gradeDtoList) {
                session.persist(GradeDao.getInstance().create(gradeDto,
                        StudentRepositoryImpl.getInstance(session).getByCriteria(gradeDto.getStudent()),
                        ThemeRepositoryImpl.getInstance(session).getByCriteria(gradeDto.getTheme())));
            }
        } catch (RuntimeException runtimeException) {
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Grade getByCriteria(String criteria) {
        Query<Grade> query = session.createQuery("select g from Grade g where g.value = ?1", Grade.class).setParameter(1, criteria);
        return query.getSingleResult();
    }

    public static GradeRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new GradeRepositoryImpl(session);
        }
        return instance;
    }
}
