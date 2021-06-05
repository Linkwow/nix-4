package ua.nix.academy.controller;

import org.hibernate.SessionFactory;
import ua.nix.academy.persistence.dto.*;

import ua.nix.academy.repository.impl.*;


import java.util.List;

public class Controller {
    private static Controller instance;
    private final SessionFactory sessionFactory;

    private Controller(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void createGroup(List<GroupDto> dtoList) throws Exception {
        GroupRepositoryImpl.getInstance(sessionFactory).create(dtoList);
    }

    public void createCourse(List<CourseDto> dtoList) throws Exception {
        CourseRepositoryImpl.getInstance(sessionFactory).create(dtoList);
    }

    public void createStudent(List<StudentDto> dtoList) throws Exception{
        StudentRepositoryImpl.getInstance(sessionFactory).create(dtoList);
    }

    public void createProfessor(List<ProfessorDto> professorDtoList) throws Exception{
        ProfessorRepositoryImpl.getInstance(sessionFactory).create(professorDtoList);
    }

    public void createTheme(List<ThemeDto> themeDtoList) throws Exception{
        ThemeRepositoryImpl.getInstance(sessionFactory).create(themeDtoList);
    }

    public void createLesson(List<LessonDto> lessonDtoList) throws Exception{
        LessonRepositoryImpl.getInstance(sessionFactory).create(lessonDtoList);
    }

    public static Controller getInstance(SessionFactory sessionfactory) {
        if(instance == null){
            instance = new Controller(sessionfactory);
        }
        return instance;
    }
}
