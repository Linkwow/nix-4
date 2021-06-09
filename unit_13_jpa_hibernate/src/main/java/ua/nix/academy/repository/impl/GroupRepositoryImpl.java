package ua.nix.academy.repository.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.academy.dao.GroupDao;
import ua.nix.academy.exception.AcademyDataAccessException;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.exception.AcademyDataException;
import ua.nix.academy.persistence.dto.GroupDto;
import ua.nix.academy.persistence.entity.*;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.List;

public class GroupRepositoryImpl implements Repository<Group, GroupDto> {
    private final Logger logger;
    private static GroupRepositoryImpl instance;
    private final Session session;

    private GroupRepositoryImpl(Session session) {
        this.session = session;
        logger = LoggerFactory.getLogger(GroupRepositoryImpl.class);
    }

    @Override
    public void create(List<GroupDto> groupDtoList) throws AcademyDataException {
        try {
            logger.info("Start creating Group entity.");
            for (GroupDto groupDto : groupDtoList) {
                Course course = CourseRepositoryImpl.getInstance(session).getByCriteria(groupDto.getCourse());
                Professor professor = ProfessorRepositoryImpl.getInstance(session).getByCriteria(groupDto.getProfessor());
                session.persist(GroupDao.getInstance().create(groupDto, course, professor));
            }
            logger.info("Create was successful.");
        } catch (RuntimeException runtimeException) {
            logger.info("Error while created.");
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        } catch (AcademyDataAccessException academyDataAccessException) {
            logger.info("Error while created.");
            throw new AcademyDataAccessException(academyDataAccessException.getMessage(), academyDataAccessException);
        }
    }

    @Override
    public Group getByCriteria(String criteria) throws AcademyDataAccessException {
        try {
            Query<Group> query = session.createQuery("select g from Group g where g.name = ?1", Group.class).setParameter(1, criteria);
            logger.info("Entity was taken successful.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.info("Entity was taken unsuccessful.");
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public Group getInfoGroup(Long professorId, Long themeId) {
        List<Theme> themes = session.createQuery("select t from Theme t " +
                "join Lesson l on l.theme.id = t.id and t.id = ?1 " +
                "join Group g on g.id = l.group.id " +
                "join Professor p on p.id = g.professor.id and p.id = ?2", Theme.class).
                setParameter(1, themeId).setParameter(2, professorId).getResultList();
        for (Theme theme : themes) {

        }

        return null;
    }

    public static GroupRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new GroupRepositoryImpl(session);
        }
        return instance;
    }
}
