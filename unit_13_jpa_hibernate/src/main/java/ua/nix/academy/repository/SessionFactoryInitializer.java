package ua.nix.academy.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInitializer {
    private static SessionFactoryInitializer instance;
    protected SessionFactory sessionFactory;
    private final Configuration configuration = new Configuration().configure();;

    private SessionFactoryInitializer(){
    }

    public void sessionFactoryOpen(){
        sessionFactory = configuration.buildSessionFactory();
    }

    public void sessionFactoryClose(){
        sessionFactory.close();
    }

    public Session openSession(){
        return sessionFactory.openSession();
    }

    public void closeSession(Session session){
        session.close();
    }

    public static SessionFactoryInitializer getInstance() {
        if(instance == null){
            instance = new SessionFactoryInitializer();
        }
        return instance;
    }
}
