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

import java.util.ArrayList;
import java.util.Collections;
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
    public Group create(GroupDto groupDto) throws AcademyDataException {
        try {
            logger.info("Start creating Group entity.");
            Course course = CourseRepositoryImpl.getInstance(session).getByCriteria(groupDto.getCourse());
            Professor professor = ProfessorRepositoryImpl.getInstance(session).getByCriteria(groupDto.getProfessor());
            Group group = GroupDao.getInstance().create(groupDto, course, professor);
            session.persist(group);
            logger.info("Create was successful.");
            return group;
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
        double mediana = 0, groupResult = 0;
        Group bestGroup = new Group();
        List<Group> groups = session.createQuery("select g from Group g " +
                "join Professor p on g.professor.id = p.id and p.id = ?1 " +
                "join Theme t on t.id = ?2 " +
                "join Student s on s.group.id = g.id ", Group.class).setParameter(1, professorId).setParameter(2, themeId).getResultList();
        Theme theme = session.createQuery("select t from Theme t where t.id = ?1", Theme.class).setParameter(1, themeId).getSingleResult();
        logger.info("Groups and Students was taken successful.");
        List<Integer> grades = new ArrayList<>();
        for (Group group : groups) {
            for (Student student : group.getStudents()) {
                for (Grade grade : student.getGrades()) {
                    if (grade.getTheme().getId().equals(theme.getId())) {
                        grades.add(grade.getValue());
                    }
                }
            }
            Collections.sort(grades);
            groupResult = getMediana(grades);
            if (mediana < groupResult) {
                mediana = groupResult;
                bestGroup = group;
            }
            grades.removeAll(grades);
        }
        return bestGroup;
    }

    private double getMediana(List<Integer> grades) {
        double mediana = 0;
        if (grades.size() % 2 == 0) {
            int leftMiddle = grades.size() / 2 - 1;
            int rightMiddle = grades.size() / 2;
            mediana = (double) (grades.get(leftMiddle) + grades.get(rightMiddle)) / 2;
        } else if (grades.size() % 2 != 0) {
            int middle = grades.size() / 2 + 1;
            mediana = (double) middle;
        }
        return mediana;
    }

    public static GroupRepositoryImpl getInstance(Session session) {
        if (instance == null) {
            instance = new GroupRepositoryImpl(session);
        }
        return instance;
    }
}
