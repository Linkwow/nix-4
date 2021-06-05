package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.nix.academy.dao.impl.GroupDaoImpl;
import ua.nix.academy.persistence.dto.GroupDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Professor;
import ua.nix.academy.repository.interfaces.Repository;


import java.util.List;

public class GroupRepositoryImpl implements Repository<Group, GroupDto, String> {
    private static GroupRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private GroupRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<GroupDto> groupDtoList) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                for (GroupDto groupDto : groupDtoList) {
                    Course course = CourseRepositoryImpl.getInstance(sessionFactory).getByCriteria(groupDto.getCourse());
                    Professor professor = ProfessorRepositoryImpl.getInstance(sessionFactory).getByCriteria(groupDto.getProfessor());
                    session.persist(GroupDaoImpl.getInstance().create(groupDto, course, professor));
                }
                session.getTransaction().commit();
            } catch (Exception sqlException) {
                session.getTransaction().rollback();
                throw new Exception(sqlException.getMessage());
            }
        }
    }

    @Override
    public Group getByCriteria(String criteria) {
        try (Session session = sessionFactory.openSession()) {
            Query<Group> groupQuery = session.createQuery("from Group where name = ?1", Group.class).
                    setParameter(1, criteria);
            return groupQuery.getSingleResult();
        }
    }

    public static GroupRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new GroupRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
