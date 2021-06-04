package ua.nix.academy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public class ProgrammingCourses {
    public static void test(String[] args) {

        Logger logger = LoggerFactory.getLogger(ProgrammingCourses.class);
        logger.debug("Test!");
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
//            for (int i = 0; i < 20; i++) {
//                Group group = new Group(1, "nix-4");
//                Student student = new Student("Serhhi Naumenko", group);
//                //session.persist(group);
//                session.persist(student);
//            }
            session.getTransaction().commit();

        }
    }

}
