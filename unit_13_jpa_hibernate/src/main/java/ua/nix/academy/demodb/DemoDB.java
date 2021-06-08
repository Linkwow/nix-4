package ua.nix.academy.demodb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.nix.academy.exception.AcademyDataAccessException;
import ua.nix.academy.exception.AcademyDataCreateException;
import ua.nix.academy.exception.AcademyDataException;
import ua.nix.academy.persistence.dto.*;
import ua.nix.academy.persistence.entity.*;
import ua.nix.academy.repository.impl.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DemoDB {
    private static Logger logger = LoggerFactory.getLogger(DemoDB.class);
    private static final List<CourseDto> courseDtoList = new ArrayList<>(Arrays.asList(
            new CourseDto("1"),
            new CourseDto("2")));

    private static final List<GroupDto> groupDtoList = new ArrayList<>(Arrays.asList(
            new GroupDto("nix-11", "1", "Mikhail Horbunov"),
            new GroupDto("nix-21", "1", "Iegor Funtusov"),
            new GroupDto("nix-12", "2", "Mikhail Horbunov"),
            new GroupDto("nix-22", "2", "Iegor Funtusov")));

    private static final List<ProfessorDto> professorDtoList = new ArrayList<>(Arrays.asList(
            new ProfessorDto("Mikhail Horbunov"),
            new ProfessorDto("Iegor Funtusov")));

    private static final List<StudentDto> studentDtoList = new ArrayList<>(Arrays.asList(
            new StudentDto("Garmash Daria", "nix-11"),
            new StudentDto("Goncharenko Evgeniya", "nix-11"),
            new StudentDto("Gorbachova Lyudmila", "nix-11"),
            new StudentDto("Grinko Nikita", "nix-11"),
            new StudentDto("Dorenskaya Daria", "nix-21"),
            new StudentDto("Kuzmin Artem", "nix-21"),
            new StudentDto("Kushik Mikhail", "nix-21"),
            new StudentDto("Naumenko Sergey", "nix-21"),
            new StudentDto("Petko Ilya", "nix-12"),
            new StudentDto("Pooh Artem", "nix-12"),
            new StudentDto("Dmitry Senkovenko", "nix-12"),
            new StudentDto("Sidora Yaroslav", "nix-12"),
            new StudentDto("Halamay Sofia", "nix-22"),
            new StudentDto("Kharakhaichuk Ivan", "nix-22"),
            new StudentDto("Chuiko Anastasia", "nix-22"),
            new StudentDto("Shirkov Andrey", "nix-22"),
            new StudentDto("Shchebetovsky Evgeny", "nix-22")));

    private static final List<ThemeDto> themeDtoList = new ArrayList<>(Arrays.asList(
            new ThemeDto("Compile"),
            new ThemeDto("Algorithmic"),
            new ThemeDto("Exam"),
            new ThemeDto("Spring"),
            new ThemeDto("Hibernate")
    ));

    private static final List<LessonDto> lessonDtoList = new ArrayList<>(Arrays.asList(
            new LessonDto("2021-02-12 19:00", "Compile", "nix-11"),
            new LessonDto("2021-02-13 14:00", "Compile", "nix-21"),
            new LessonDto("2021-02-14 19:00", "Compile", "nix-12"),
            new LessonDto("2021-02-15 14:00", "Compile", "nix-22"),
            new LessonDto("2021-03-12 21:00", "Algorithmic", "nix-11"),
            new LessonDto("2021-03-13 21:00", "Algorithmic", "nix-21"),
            new LessonDto("2021-03-14 19:00", "Algorithmic", "nix-12"),
            new LessonDto("2021-03-15 14:00", "Algorithmic", "nix-22"),
            new LessonDto("2021-04-12 19:00", "Exam", "nix-11"),
            new LessonDto("2021-04-13 19:00", "Exam", "nix-21"),
            new LessonDto("2021-04-14 19:00", "Exam", "nix-12"),
            new LessonDto("2021-04-15 19:00", "Exam", "nix-22"),
            new LessonDto("2021-08-01 15:00", "Spring", "nix-11"),
            new LessonDto("2021-08-02 15:00", "Spring", "nix-21"),
            new LessonDto("2021-08-03 15:00", "Spring", "nix-12"),
            new LessonDto("2021-08-04 15:00", "Spring", "nix-22"),
            new LessonDto("2021-09-11 12:00", "Hibernate", "nix-11"),
            new LessonDto("2021-09-12 12:00", "Hibernate", "nix-21"),
            new LessonDto("2021-09-13 12:00", "Hibernate", "nix-12"),
            new LessonDto("2021-09-14 12:00", "Hibernate", "nix-22")
    ));
    private static final List<GradeDto> gradeDtoList = new ArrayList<>(Arrays.asList(
            new GradeDto("5", "Garmash Daria", "Exam"),
            new GradeDto("4", "Goncharenko Evgeniya", "Exam"),
            new GradeDto("3", "Gorbachova Lyudmila", "Exam"),
            new GradeDto("2", "Grinko Nikita", "Exam"),
            new GradeDto("5", "Dorenskaya Daria", "Exam"),
            new GradeDto("3", "Kuzmin Artem", "Exam"),
            new GradeDto("3", "Kushik Mikhail", "Exam"),
            new GradeDto("2", "Naumenko Sergey", "Exam"),
            new GradeDto("4", "Petko Ilya", "Exam"),
            new GradeDto("5", "Pooh Artem", "Exam"),
            new GradeDto("5", "Dmitry Senkovenko", "Exam"),
            new GradeDto("4", "Sidora Yaroslav", "Exam"),
            new GradeDto("3", "Halamay Sofia", "Exam"),
            new GradeDto("5", "Kharakhaichuk Ivan", "Exam"),
            new GradeDto("4", "Chuiko Anastasia", "Exam"),
            new GradeDto("2", "Shirkov Andrey", "Exam"),
            new GradeDto("4", "Shchebetovsky Evgeny", "Exam")));

    private static void create(Session session) throws AcademyDataAccessException {
        try {
            logger.info("Start create relations.");
            List<Course> courseList = session.createQuery("select c from Course c", Course.class).getResultList();
            List<Grade> gradeList = session.createQuery("select g from Grade g", Grade.class).getResultList();
            List<Group> groupList = session.createQuery("select g from Group g", Group.class).getResultList();
            List<Lesson> lessonList = session.createQuery("select l from Lesson l", Lesson.class).getResultList();
            List<Professor> professorList = session.createQuery("select p from Professor p", Professor.class).getResultList();
            List<Student> studentList = session.createQuery("select s from Student s", Student.class).getResultList();
            List<Theme> themeList = session.createQuery("select t from Theme t", Theme.class).getResultList();
            for (Group group : groupList) {
                for (Course course : courseList) {
                    if (group.getCourse().getId().equals(course.getId())) {
                        course.setGroupList(group);
                    }
                }
                for (Student student : studentList) {
                    if (group.getId().equals(student.getGroup().getId())) {
                        student.setGroup(group);
                    }
                }
                for (Professor professor : professorList) {
                    if (group.getProfessor().getId().equals(professor.getId())) {
                        professor.setGroups(group);
                    }
                }
                for (Lesson lesson : lessonList) {
                    if (lesson.getGroup().getId().equals(group.getId())) {
                        group.setLessons(lesson);
                    }
                }
            }
            for (Theme theme : themeList) {
                for (Grade grade : gradeList) {
                    if(grade.getTheme().getId().equals(theme.getId())){
                        theme.setGrades(grade);
                    }
                }
            }
            for(Student student : studentList){
                for(Grade grade : gradeList){
                    if(grade.getStudent().getId().equals(student.getId())) {
                        student.setGrades(grade);
                    }
                }
            }
            logger.info("Create relations successful");
        } catch (Exception exception) {
            logger.error("Create relations between courses and groups was failed");
            throw new AcademyDataAccessException(exception.getMessage(), exception);
        }
    }

    public static void createEntities(Session session) throws AcademyDataException {
        try {
            CourseRepositoryImpl.getInstance(session).create(courseDtoList);
            ProfessorRepositoryImpl.getInstance(session).create(professorDtoList);
            GroupRepositoryImpl.getInstance(session).create(groupDtoList);
            StudentRepositoryImpl.getInstance(session).create(studentDtoList);
            ThemeRepositoryImpl.getInstance(session).create(themeDtoList);
            LessonRepositoryImpl.getInstance(session).create(lessonDtoList);
            GradeRepositoryImpl.getInstance(session).create(gradeDtoList);
            create(session);
        } catch (AcademyDataException academyDataException) {
            throw new AcademyDataException(academyDataException.getMessage(), academyDataException);
        }
    }
}
