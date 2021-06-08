package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.nix.academy.dao.GroupDao;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.persistence.dto.GroupDto;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Professor;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;
import ua.nix.libs.MathSet;

public class GroupRepositoryImpl implements Repository<Group, GroupDto> {
    private static GroupRepositoryImpl instance;
    private Session session;

    private GroupRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void create(List<GroupDto> groupDtoList) throws AcademyDataCreateException {
        try {
            for (GroupDto groupDto : groupDtoList) {
                Course course = CourseRepositoryImpl.getInstance(session).getByCriteria(groupDto.getCourse());
                Professor professor = ProfessorRepositoryImpl.getInstance(session).getByCriteria(groupDto.getProfessor());
                session.persist(GroupDao.getInstance().create(groupDto, course, professor));
            }
        } catch (RuntimeException runtimeException) {
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        }
    }

    @Override
    public Group getByCriteria(String criteria) {
        Query<Group> query = session.createQuery("select g from Group g where g.name = ?1", Group.class).setParameter(1, criteria);
        return query.getSingleResult();
    }

    public Group getInfoGroup(Long id){
        List<Group> groups = session.createQuery("select g from Group g join Professor p on g.professor.id = p.id where p.id = ?1", Group.class).
                setParameter(1, id).getResultList();


    }

    public static GroupRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new GroupRepositoryImpl(session);
        }
        return instance;
    }
}
