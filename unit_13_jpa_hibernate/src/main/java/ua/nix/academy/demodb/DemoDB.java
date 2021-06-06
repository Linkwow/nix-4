package ua.nix.academy.demodb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.nix.academy.persistence.dto.*;
import ua.nix.academy.persistence.entity.Course;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.repository.impl.*;
import ua.nix.academy.ui.UserInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoDB {
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
            new ThemeDto("Module-1"),
            new ThemeDto("Exception"),
            new ThemeDto("Collection"),
            new ThemeDto("Module-2"),
            new ThemeDto("Spring"),
            new ThemeDto("Hibernate")

    ));

    private static final List<LessonDto> lessonDtoList = new ArrayList<>(Arrays.asList(
            new LessonDto("2021-02-12 19:00", "Compile", "Iegor Funtusov"),
            new LessonDto("2021-02-14 14:00", "Algorithmic", "Iegor Funtusov"),
            new LessonDto("2021-03-12 19:00", "Module-1", "Iegor Funtusov"),
            new LessonDto("2021-03-18 14:00", "Exception", "Mikhail Horbunov"),
            new LessonDto("2021-04-10 21:00", "Collection", "Mikhail Horbunov"),
            new LessonDto("2021-04-10 21:00", "Module-2", "Mikhail Horbunov"),
            new LessonDto("2021-07-02 15:00", "Spring", "Mikhail Horbunov"),
            new LessonDto("2021-07-11 12:00", "Hibernate", "Iegor Funtusov")
    ));

    private static final List<GradeDto> gradeDtoList = new ArrayList<>(Arrays.asList(
            new GradeDto("0"),
            new GradeDto("6"),
            new GradeDto("7"),
            new GradeDto("8"),
            new GradeDto("9"),
            new GradeDto("10")));

    private static List<CourseDto> getCourseDtoList() {
        return courseDtoList;
    }

    private static List<GroupDto> getGroupDtoList() {
        return groupDtoList;
    }

    private static List<ProfessorDto> getProfessorDtoList() {
        return professorDtoList;
    }

    private static List<StudentDto> getStudentDtoList() {
        return studentDtoList;
    }

    private static List<ThemeDto> getThemeDtoList() {
        return themeDtoList;
    }

    private static List<LessonDto> getLessonDtoList() {
        return lessonDtoList;
    }

    private static List<GradeDto> getGradeDtoList() {
        return gradeDtoList;
    }

    private static void updateCourse(SessionFactory sessionFactory) {
        Logger logger = LoggerFactory.getLogger(DemoDB.class);
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                List<Course> courseList = session.createQuery("select c from Course c", Course.class).getResultList();
                List<Group> groupList = session.createQuery("select g from Group g", Group.class).getResultList();
                for (Course course : courseList) {
                    for (Group group : groupList) {
                        if (course.getCourseNumber().equals(group.getCourse().getCourseNumber())) {
                            course.setGroupList(group);
                        }
                    }
                }
                session.getTransaction().commit();
                logger.info("Update course and groups table successful");
            } catch (Exception exception) {
                session.getTransaction().rollback();
                exception.printStackTrace();
                logger.error("Update course and groups table was failed");
            }
        }
    }

    public static void createEntities(SessionFactory sessionFactory) throws Exception {
        CourseRepositoryImpl.getInstance(sessionFactory).create(getCourseDtoList());
        ProfessorRepositoryImpl.getInstance(sessionFactory).create(DemoDB.getProfessorDtoList());
        GroupRepositoryImpl.getInstance(sessionFactory).create(DemoDB.getGroupDtoList());
        StudentRepositoryImpl.getInstance(sessionFactory).create(DemoDB.getStudentDtoList());
        ThemeRepositoryImpl.getInstance(sessionFactory).create(DemoDB.getThemeDtoList());
        LessonRepositoryImpl.getInstance(sessionFactory).create(DemoDB.getLessonDtoList());
        GradeRepositoryImpl.getInstance(sessionFactory).create(DemoDB.getGradeDtoList());
        updateCourse(sessionFactory);
    }


}
