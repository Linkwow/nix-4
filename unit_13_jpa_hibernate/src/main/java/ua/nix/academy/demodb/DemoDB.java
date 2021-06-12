package ua.nix.academy.demodb;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.nix.academy.exception.AcademyDataException;
import ua.nix.academy.persistence.dto.*;
import ua.nix.academy.persistence.entity.*;
import ua.nix.academy.repository.impl.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoDB {
    private static final Logger logger = LoggerFactory.getLogger(DemoDB.class);
    private static final List<CourseDto> courseDtoList = new ArrayList<>(Arrays.asList(
            new CourseDto("1"),
            new CourseDto("2")
    ));

    private static final List<GroupDto> groupDtoList = new ArrayList<>(Arrays.asList(
            new GroupDto("nix-11", "1", "Mikhail Horbunov"),
            new GroupDto("nix-21", "1", "Iegor Funtusov"),
            new GroupDto("nix-12", "2", "Mikhail Horbunov"),
            new GroupDto("nix-22", "2", "Iegor Funtusov")
    ));

    private static final List<ProfessorDto> professorDtoList = new ArrayList<>(Arrays.asList(
            new ProfessorDto("Mikhail Horbunov"),
            new ProfessorDto("Iegor Funtusov")
    ));

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
            new GradeDto("5", "Exam", "Garmash Daria"),
            new GradeDto("4", "Exam", "Goncharenko Evgeniya"),
            new GradeDto("3", "Exam", "Gorbachova Lyudmila"),
            new GradeDto("2", "Exam", "Grinko Nikita"),
            new GradeDto("5", "Exam", "Dorenskaya Daria"),
            new GradeDto("3", "Exam", "Kuzmin Artem"),
            new GradeDto("3", "Exam", "Naumenko Sergey"),
            new GradeDto("2", "Exam", "Petko Ilya"),
            new GradeDto("4", "Exam", "Pooh Artem"),
            new GradeDto("5", "Exam", "Dmitry Senkovenko"),
            new GradeDto("5", "Exam", "Sidora Yaroslav"),
            new GradeDto("4", "Exam", "Halamay Sofia"),
            new GradeDto("3", "Exam", "Kharakhaichuk Ivan"),
            new GradeDto("5", "Exam", "Chuiko Anastasia"),
            new GradeDto("4", "Exam", "Shirkov Andrey"),
            new GradeDto("2", "Exam", "Shchebetovsky Evgeny"),
            new GradeDto("4", "Exam", "Kushik Mikhail"),
            new GradeDto("5", "Spring", "Garmash Daria"),
            new GradeDto("4", "Spring", "Goncharenko Evgeniya"),
            new GradeDto("3", "Spring", "Gorbachova Lyudmila"),
            new GradeDto("2", "Spring", "Grinko Nikita"),
            new GradeDto("5", "Spring", "Dorenskaya Daria"),
            new GradeDto("3", "Spring", "Kuzmin Artem"),
            new GradeDto("3", "Spring", "Naumenko Sergey"),
            new GradeDto("2", "Spring", "Petko Ilya"),
            new GradeDto("4", "Spring", "Pooh Artem"),
            new GradeDto("5", "Spring", "Dmitry Senkovenko"),
            new GradeDto("5", "Spring", "Sidora Yaroslav"),
            new GradeDto("4", "Spring", "Halamay Sofia"),
            new GradeDto("3", "Spring", "Kharakhaichuk Ivan"),
            new GradeDto("5", "Spring", "Chuiko Anastasia"),
            new GradeDto("4", "Spring", "Shirkov Andrey"),
            new GradeDto("2", "Spring", "Shchebetovsky Evgeny"),
            new GradeDto("4", "Spring", "Kushik Mikhail")
    ));

    public static void createEntities(Session session) throws AcademyDataException {
        try {
            List<Course> courseList = new ArrayList<>();
            for (CourseDto courseDto : courseDtoList) {
                courseList.add(CourseRepositoryImpl.getInstance(session).create(courseDto));
            }
            List<Professor> professorList = new ArrayList<>();
            for (ProfessorDto professorDto : professorDtoList) {
                professorList.add(ProfessorRepositoryImpl.getInstance(session).create(professorDto));
            }
            List<Group> groupList = new ArrayList<>();
            for (GroupDto groupDto : groupDtoList) {
                groupList.add(GroupRepositoryImpl.getInstance(session).create(groupDto));
            }
            List<Student> studentList = new ArrayList<>();
            for (StudentDto studentDto : studentDtoList) {
                studentList.add(StudentRepositoryImpl.getInstance(session).create(studentDto));
            }
            List<Theme> themeList = new ArrayList<>();
            for (ThemeDto themeDto : themeDtoList) {
                themeList.add(ThemeRepositoryImpl.getInstance(session).create(themeDto));
            }
            List<Lesson> lessonList = new ArrayList<>();
            for (LessonDto lessonDto : lessonDtoList){
                lessonList.add(LessonRepositoryImpl.getInstance(session).create(lessonDto));
            }
            List<Grade> gradeList = new ArrayList<>();
            for(GradeDto gradeDto : gradeDtoList){
                gradeList.add(GradeRepositoryImpl.getInstance(session).create(gradeDto));
            }
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
                    if (grade.getTheme().getId().equals(theme.getId())) {
                        theme.setGrades(grade);
                    }
                }
            }
            for(Student student : studentList){
                for(Grade grade : gradeList){
                    if(grade.getStudent().getId().equals(student.getId())){
                        student.setGrades(grade);
                    }
                }
            }
            logger.info("Create relations successful");
        } catch (AcademyDataException academyDataException) {
            throw new AcademyDataException(academyDataException.getMessage(), academyDataException);
        }
    }

}
