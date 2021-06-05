package ua.nix.academy.demodb;

import ua.nix.academy.persistence.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoDB {
    private static final List<CourseDto> courseDtoList = new ArrayList<>(Arrays.asList(
            new CourseDto(1),
            new CourseDto(2)));

    private static final List<GroupDto> groupDtoList = new ArrayList<>(Arrays.asList(
            new GroupDto("nix-11", 1, "Mikhail Horbunov"),
            new GroupDto("nix-21", 1, "Iegor Funtusov"),
            new GroupDto("nix-12", 2, "Mikhail Horbunov"),
            new GroupDto("nix-22", 2, "Iegor Funtusov")));

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
            new LessonDto("2021-02-12 19:00", "Compile"),
            new LessonDto("2021-02-14 14:00", "Algorithmic"),
            new LessonDto("2021-03-12 19:00", "Module-1"),
            new LessonDto("2021-03-18 14:00", "Exception"),
            new LessonDto("2021-04-10 21:00", "Collection"),
            new LessonDto("2021-04-10 21:00", "Module-2"),
            new LessonDto("2021-07-02 15:00", "Spring"),
            new LessonDto("2021-07-11 12:00", "Hibernate")
    ));

    public static List<CourseDto> getCourseDtoList() {
        return courseDtoList;
    }

    public static List<GroupDto> getGroupDtoList() {
        return groupDtoList;
    }

    public static List<ProfessorDto> getProfessorDtoList() {
        return professorDtoList;
    }

    public static List<StudentDto> getStudentDtoList() {
        return studentDtoList;
    }

    public static List<ThemeDto> getThemeDtoList() {
        return themeDtoList;
    }

    public static List<LessonDto> getLessonDtoList() {
        return lessonDtoList;
    }
}
