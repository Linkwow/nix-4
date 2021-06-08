package ua.nix.academy.ui;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.academy.controller.Controller;
import ua.nix.academy.demodb.DemoDB;
import ua.nix.academy.exception.AcademyDataException;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Lesson;

import java.util.Scanner;

public class UserInterface {
    private final Logger logger;
    private static UserInterface instance;
    private boolean stillWorking = true;

    private UserInterface() {
        logger = LoggerFactory.getLogger(UserInterface.class);
        logger.info("UserInterface instance was created.");
    }

    public void start() throws Exception {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            logger.info("Session factory initialize successful. Session open correctly");
            try {
                session.getTransaction().begin();
                DemoDB.createEntities(session);
                logger.info("Create Demo data base was successful.");
                session.getTransaction().commit();
                while (stillWorking) {
                    switch (operation()) {
                        case 1:
                            System.out.println("Enter id of student");
                            Lesson lesson = Controller.studentInfo(session, new Scanner(System.in).nextLong());
                            System.out.println("lesson id " + lesson.getId() +
                                    " lesson date and time " + lesson.getZonedDateTime().toString() +
                                    " theme " + lesson.getTheme().getName() + " professor " +
                                    lesson.getGroup().getProfessor().getInitials());
                            break;
                        case 2:
                            System.out.println("Enter id of professor");
                            Long professorId = new Scanner(System.in).nextLong();
                            Long themeID = new Scanner(System.in).nextLong();
                            Group group = Controller.groupInfo(session,professorId, themeID);
                            System.out.println("group name " + group.getName() + " course " + group.getCourse().getCourseNumber());
                        break;
                        default:
                            stillWorking = false;
                            break;
                    }
                }
            } catch (AcademyDataException academyDataException) {
                academyDataException.printStackTrace();
                session.getTransaction().rollback();
            }
        }
    }

    private int operation() {
        System.out.println("""
                Choose operation on entity (or enter 3 to exit):
                1. Get information about lesson by student id;
                2. Get information about the most success group by professor id.
                """);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static UserInterface getInstance() {
        if (instance == null) {
            instance = new UserInterface();
        }
        return instance;
    }
}
