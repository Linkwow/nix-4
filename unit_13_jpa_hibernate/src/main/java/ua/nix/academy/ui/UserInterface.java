package ua.nix.academy.ui;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.academy.controller.Controller;
import ua.nix.academy.demodb.DemoDB;

import java.util.Scanner;

public class UserInterface {
    private Logger logger;
    private static UserInterface instance;
    private boolean outerMenu = true;
    private boolean innerMenu = true;

    private UserInterface() {
        logger = LoggerFactory.getLogger(UserInterface.class);
        logger.info("UserInterface instance was created.");
    }

    public void start() throws Exception {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            DemoDB.createEntities(sessionFactory);
            logger.info("SessionFactory was initialized.");
            while (outerMenu) {
                logger.info("Enter at the outer menu.");
                int repositoryNum = showEntities();
                if (repositoryNum == 8) {
                    outerMenu = false;
                    logger.info("Exit from the outer menu.");
                } else {
                    innerMenu = true;
                    Controller.repositoryCreate(repositoryNum, sessionFactory);
                    while (innerMenu) {
                        logger.info("Enter at the inner menu.");
                        int operation = operationOnEntities();
                        switch (operation) {
                            case 1:
                                Controller.create(repositoryNum);
                                break;
                            case 2:
                                Controller.readOneById();
                                break;
                            case 3:
                                Controller.readOneByCriteria();
                                break;
                            case 4:
                                Controller.updateById();
                                break;
                            case 5:
                                Controller.deleteById();
                                break;
                            case 6:
                                Controller.getInfo(sessionFactory);
                                break;
                            case 7:
                                break;
                            case 8:
                                innerMenu = false;
                                logger.info("Exit from the inner menu.");
                                break;
                        }
                    }
                }
            }
        }
    }


    private int showEntities() {
        System.out.println("""
                Choose entity for operations: 
                1. Course.
                2. Group.
                3. Student. 
                4. Professor.
                5. Lesson.
                6. Theme.
                7. Grade.
                8. Exit.""");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private int operationOnEntities() {
        System.out.println("""
                Choose operation on entity:
                1.Create.
                2.Read one by id (Find one by id).
                3.Read one by criteria(Find one by criteria).
                4.Update by id;
                5.Delete by id.
                6.Get information about lesson by student id;
                7.
                8.End.
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
