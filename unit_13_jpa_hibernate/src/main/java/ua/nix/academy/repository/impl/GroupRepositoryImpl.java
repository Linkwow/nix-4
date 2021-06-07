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
import java.util.Scanner;

public class GroupRepositoryImpl implements Repository<Group, GroupDto> {
    private static GroupRepositoryImpl instance;
    private final SessionFactory sessionFactory;

    private GroupRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(List<GroupDto> groupDtoList) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.getTransaction().begin();
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
            Query<Group> query = session.createQuery("select g from Group g where g.name = ?1", Group.class).setParameter(1, criteria);
            return query.getSingleResult();
        }
    }

    @Override
    public Group getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Group> query = session.createQuery("select g from Group g where g.id = ?1", Group.class).setParameter(1, id);
            return query.getSingleResult();
        }
    }

    @Override
    public void updateById(Long id) {
        System.out.println("Enter a new group name, or press the enter to left old value");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if (value != null) {
            try (Session session = sessionFactory.openSession()) {
                try {
                    session.getTransaction().begin();
                    Query<Group> query = session.createQuery("select g from Group g where g.id = ?1", Group.class).setParameter(1, id);
                   Group group = query.getSingleResult();
                   group.setName(value);
                   session.saveOrUpdate(group);
                   session.getTransaction().commit();
                } catch (Exception e){
                    e.printStackTrace();
                    session.getTransaction().rollback();
                }
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.getTransaction().begin();
                Query<Group> query = session.createQuery("select g from Group g where g.id = ?1", Group.class).setParameter(1, id);
                Group group = query.getSingleResult();
                session.remove(group);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }

    public static GroupRepositoryImpl getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new GroupRepositoryImpl(sessionFactory);
        }
        return instance;
    }
}
