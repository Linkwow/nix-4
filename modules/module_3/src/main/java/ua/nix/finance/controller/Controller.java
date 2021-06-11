package ua.nix.finance.controller;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nix.finance.exceptions.FinanceExceptions;
import ua.nix.finance.exceptions.JDBCConnectionException;
import ua.nix.finance.service.HibernateService;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class Controller {
    private static Controller instance;
    private Configuration configuration;
    private Properties properties;
    private StringBuilder userName;
    private StringBuilder password;
    private final Logger logger;
    private final ValidatorFactory validatorFactory;

    private Controller() {
        logger = LoggerFactory.getLogger(Controller.class);
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    public void providerStart(Integer providerChoice, String[] dbParameters, String[] entityParameters) {
        try {
            Long id = Optional.of(Long.parseLong(dbParameters[0])).orElseThrow();
            userName = new StringBuilder(Optional.of(dbParameters[1]).orElseThrow());
            password = new StringBuilder(Optional.of(dbParameters[2]).orElseThrow());
            if (providerChoice == 1) {
                logger.info("Start to configure connection");
                configuration = new Configuration().configure();
                configuration.setProperty("hibernate.connection.username", userName.toString());
                configuration.setProperty("hibernate.connection.password", password.toString());
                try (SessionFactory sessionFactory = configuration.buildSessionFactory();
                     Session session = sessionFactory.openSession()) {
                    try {
                        session.getTransaction().begin();
                        HibernateService.getInstance(session, validatorFactory).createTransaction(id, entityParameters);
                        session.getTransaction().commit();
                    } catch (FinanceExceptions financeExceptions) {
                        financeExceptions.printStackTrace();
                        session.getTransaction().rollback();
                        throw new FinanceExceptions(financeExceptions.getMessage(), financeExceptions);
                    }
                }
            } else if (providerChoice == 2) {
               /* try {
                    createJDBCConnection();
                    try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {

                    }
                }*/
            }


        } catch (FinanceExceptions | RuntimeException exception) {
            System.err.println(exception.getMessage());
        } finally {
            userName.delete(0, userName.length() - 1);
            password.delete(0, password.length() - 1);
        }
    }

    private void createJDBCConnection() throws JDBCConnectionException {
        properties = new Properties();
        try (InputStream inputStream = HibernateService.class.getResourceAsStream("/jdbc.properties")) {
            logger.info("Start initialize JDBC connection");
            properties.load(inputStream);
            properties.setProperty("user", userName.toString());
            properties.setProperty("password", password.toString());
        } catch (IOException | RuntimeException exception) {
            throw new JDBCConnectionException(exception.getMessage(), exception);
        }
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public static void main(String[] args) {
        String[] dbParam = new String[]{"1", "postgres", "postgresql"};
        String[] entityParam = new String[]{"-150", "2011-12-03T10:11:30", "FOOD", "SPORT"};
        Controller.getInstance().providerStart(1, dbParam, entityParam);
    }

}
