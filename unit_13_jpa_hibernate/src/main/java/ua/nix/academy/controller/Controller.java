package ua.nix.academy.controller;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.academy.persistence.dto.*;

import ua.nix.academy.repository.impl.*;
import ua.nix.academy.repository.interfaces.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private static Repository repository;
    private static AbstractDto abstractDto;

    public static void repositoryCreate(int repositoryNum, SessionFactory sessionFactory) {
        switch (repositoryNum) {
            case 1:
                repository = CourseRepositoryImpl.getInstance(sessionFactory);
                logger.info("CourseRepositoryImpl was chosen.");
                break;
            case 2:
                repository = GroupRepositoryImpl.getInstance(sessionFactory);
                logger.info("GroupRepositoryImpl was chosen.");
                break;
            case 3:
                repository = StudentRepositoryImpl.getInstance(sessionFactory);
                logger.info("StudentRepositoryImpl was chosen.");
                break;
            case 4:
                repository = ProfessorRepositoryImpl.getInstance(sessionFactory);
                logger.info("ProfessorRepositoryImpl was chosen.");
                break;
            case 5:
                repository = LessonRepositoryImpl.getInstance(sessionFactory);
                logger.info("LessonRepositoryImpl was chosen.");
                break;
            case 6:
                repository = ThemeRepositoryImpl.getInstance(sessionFactory);
                logger.info("ThemeRepositoryImpl was chosen.");
                break;
            case 7:
                repository = GradeRepositoryImpl.getInstance(sessionFactory);
                logger.info("GradeRepositoryImpl was chosen.");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + repositoryNum);
        }
    }

    @SuppressWarnings("unchecked")
    public static void create(int repositoryNum) {
        List<AbstractDto> abstractDtoList = new ArrayList<>();
        abstractDtoList.add(createDto(repositoryNum));
        try {
            repository.create(abstractDtoList);
            logger.info("Create operation successful.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void readOneById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of entity");
        repository.getById(scanner.nextLong());
    }

    public static void readOneByCriteria(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the criteria of entity");
        repository.getByCriteria(scanner.nextLine());
    }

    public static void readAllByCriteria(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the criteria of entity");
        repository.getAllByCriteria(scanner.nextLine());
    }

    public static void updateById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of entity");
        repository.updateById(scanner.nextLong());
    }

    public static void deleteById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the id of entity");
        repository.deleteById(scanner.nextLong());
    }

    private static AbstractDto createDto(int dtoNum) {
        Scanner scanner = new Scanner(System.in);
        String name, number, initials, time;
        switch (dtoNum) {
            case 1:
                System.out.println("Enter a course number");
                logger.info("CourseDto was created.");
                return new CourseDto(scanner.nextLine());
            case 2:
                System.out.println("Enter a group name");
                name = scanner.nextLine();
                System.out.println("Enter a course number");
                number = scanner.nextLine();
                System.out.println("Enter a professor initials");
                initials = scanner.nextLine();
                logger.info("GroupDto was created.");
                return new GroupDto(name, number, initials);
            case 3:
                System.out.println("Enter a student initials");
                initials = scanner.nextLine();
                System.out.println("Enter a group name");
                name = scanner.nextLine();
                logger.info("StudentDto was created.");
                return new StudentDto(initials, name);
            case 4:
                System.out.println("Enter a professor initials");
                initials = scanner.nextLine();
                logger.info("ProfessorDto was created.");
                return new ProfessorDto(initials);
            case 5:
                System.out.println("Enter a lesson time in format yyyy-mm-dd hh:mm");
                time = scanner.nextLine();
                System.out.println("Enter a lesson theme");
                name = scanner.nextLine();
                System.out.println("Enter a Professor initials");
                initials = scanner.nextLine();
                logger.info("LessonDto was created.");
                return new LessonDto(time, name, initials);
            case 6:
                System.out.println("Enter a theme");
                name = scanner.nextLine();
                logger.info("ThemeDto was created.");
                return new ThemeDto(name);
            case 7:
                System.out.println("Enter a grade value");
                number = scanner.nextLine();
                logger.info("GradeDto was chosen.");
                return new GradeDto(number);
            default:
                throw new IllegalStateException("Unexpected value: " + dtoNum);
        }
    }
}