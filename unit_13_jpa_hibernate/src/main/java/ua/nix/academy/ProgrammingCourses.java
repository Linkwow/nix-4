package ua.nix.academy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import ua.nix.academy.controller.Controller;
import ua.nix.academy.demodb.DemoDB;

public class ProgrammingCourses {
    public static void main(String[] args) throws Exception  {
        Logger logger = LoggerFactory.getLogger(ProgrammingCourses.class);
        logger.debug("Test!");
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            Controller controller = Controller.getInstance(sessionFactory);
            controller.createCourse(DemoDB.getCourseDtoList());
            controller.createProfessor(DemoDB.getProfessorDtoList());
            controller.createGroup(DemoDB.getGroupDtoList());
            controller.createStudent(DemoDB.getStudentDtoList());
            controller.createTheme(DemoDB.getThemeDtoList());
            controller.createLesson(DemoDB.getLessonDtoList());

        }
    }


}
