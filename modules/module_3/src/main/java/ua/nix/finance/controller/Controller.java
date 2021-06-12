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
import ua.nix.finance.persistence.entity.DischargeDto;
import ua.nix.finance.service.HibernateService;
import ua.nix.finance.service.JDBCService;
import ua.nix.finance.util.Writer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class Controller {
    private static Controller instance;
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
                Configuration configuration = new Configuration().configure();
                configuration.setProperty("hibernate.connection.username", userName.toString());
                configuration.setProperty("hibernate.connection.password", password.toString());
                logger.info("Connection configure successful");
                try (SessionFactory sessionFactory = configuration.buildSessionFactory();
                     Session session = sessionFactory.openSession()) {
                    logger.info("Session open successful");
                    try {
                        session.getTransaction().begin();
                        HibernateService.getInstance(session, validatorFactory).createTransaction(id, entityParameters);
                        session.getTransaction().commit();
                        logger.info("Transaction commit successful");
                    } catch (FinanceExceptions financeExceptions) {
                        session.getTransaction().rollback();
                        logger.error("Abort commit to data base");
                        throw new FinanceExceptions(financeExceptions.getMessage(), financeExceptions);
                    }
                }
            } else if (providerChoice == 2) {
                try {
                    createJDBCConnection();
                    try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
                        List<DischargeDto> dischargeDtoList = JDBCService.getInstance(connection).getDischarge(id, entityParameters[0], entityParameters[1]);
                        Integer debet = JDBCService.getInstance(connection).getDebet(id, entityParameters[0], entityParameters[1]);
                        Integer balance = JDBCService.getInstance(connection).getBalance(id, debet, entityParameters[0], entityParameters[1]);
                        Writer.writeBalanceToFile(dischargeDtoList, debet, balance);
                    }
                } catch (SQLException exception) {
                    throw new RuntimeException();
                }
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
            logger.info("Connection to JDBC successful");
        } catch (IOException | RuntimeException exception) {
            logger.error("Connection to JDBC unsuccessful");
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
        String[] dbParam = new String[]{"2", "postgres", "postgresql"};
        String[] entityParam = new String[]{"1", "2010-12-03T10:11:30", "2012-12-31T10:11:30"};
        // Controller.getInstance().providerStart(1, dbParam, entityParam);
        Controller.getInstance().providerStart(2, dbParam, entityParam);
    }
}
