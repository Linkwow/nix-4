package ua.nix.academy.controller;

import org.hibernate.Session;
import ua.nix.academy.exception.AcademyDataAccessException;
import ua.nix.academy.exception.AcademyDataException;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Lesson;
import ua.nix.academy.repository.impl.GroupRepositoryImpl;
import ua.nix.academy.repository.impl.StudentRepositoryImpl;

public class Controller {

    public static Lesson studentInfo(Session session, long id) throws AcademyDataException {
        try {
            return StudentRepositoryImpl.getInstance(session).takeInfoAboutLesson(id);
        } catch (AcademyDataException academyDataException) {
            throw new AcademyDataAccessException(academyDataException.getMessage(), academyDataException);
        }
    }

    public static Group groupInfo(Session session, long professorId, long themeId){
        return GroupRepositoryImpl.getInstance(session).getInfoGroup(professorId, themeId);
    }
}