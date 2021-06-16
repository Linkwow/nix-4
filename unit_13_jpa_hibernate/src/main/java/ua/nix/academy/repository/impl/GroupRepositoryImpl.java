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
    private final Session session;
    private static final Logger logger = LoggerFactory.getLogger(GroupRepositoryImpl.class);

    public GroupRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Group create(GroupDto groupDto) throws AcademyDataException {
        try {
            logger.info("Creating Group entity.");
            CourseRepositoryImpl courseRepository = new CourseRepositoryImpl(session);
            Course course = courseRepository.getCourseByNumber(groupDto.getCourse());
            Professor professor = new ProfessorRepositoryImpl(session).getProfessorByInitials(groupDto.getProfessor());
            Group group = GroupDao.getInstance().create(groupDto, course, professor);
            session.persist(group);
            logger.info("Group entity was created successfully.");
            return group;
        } catch (RuntimeException runtimeException) {
            logger.error("Group entity wasn't created.", runtimeException);
            throw new AcademyDataCreateException(runtimeException.getMessage(), runtimeException);
        } catch (AcademyDataAccessException academyDataAccessException) {
            logger.error("Group entity wasn't created.", academyDataAccessException);
            throw new AcademyDataAccessException(academyDataAccessException.getMessage(), academyDataAccessException);
        }
    }

    public Group getGroupByName(String groupName) throws AcademyDataAccessException {
        try {
            logger.info("Searching Group by name.");
            Query<Group> query = session.createQuery("select g from Group g where g.name = ?1", Group.class).setParameter(1, groupName);
            logger.info("Group was found.");
            return query.getSingleResult();
        } catch (RuntimeException runtimeException) {
            logger.error("Group wasn't found.", runtimeException);
            throw new AcademyDataAccessException(runtimeException.getMessage(), runtimeException);
        }
    }

    public Group getMostSuccessfulGroup(Long professorId, Long themeId) {
        double median = 0, groupResult;
        Group bestGroup = new Group();
        List<Group> groups = session.createQuery("select g from Group g " +
                "join Professor p on g.professor.id = p.id and p.id = ?1 " +
                "join Theme t on t.id = ?2 " +
                "join Student s on s.group.id = g.id ", Group.class).setParameter(1, professorId).setParameter(2, themeId).getResultList();
        logger.info("Groups and Students was taken successful.");
        List<Integer> grades = new ArrayList<>();
        for (Group group : groups) {
            for (Student student : group.getStudents()) {
                for (Grade grade : student.getGrades()) {
                    if (grade.getTheme().getId().equals(themeId)) {
                        grades.add(grade.getValue());
                    }
                }
            }
            Collections.sort(grades);
            groupResult = getMedian(grades);
            if (median < groupResult) {
                median = groupResult;
                bestGroup = group;
            }
            grades.clear();
        }
        return bestGroup;
    }

    private double getMedian(List<Integer> grades) {
        double median;
        if (grades.size() % 2 == 0) {
            int leftMiddle = grades.size() / 2 - 1;
            int rightMiddle = grades.size() / 2;
            median = (double) (grades.get(leftMiddle) + grades.get(rightMiddle)) / 2;
        } else {
            median = (double) grades.size() / 2 + 1;
        }
        return median;
    }
}
