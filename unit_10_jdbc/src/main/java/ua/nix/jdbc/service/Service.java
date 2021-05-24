package ua.nix.jdbc.service;

import ua.nix.jdbc.dao.DaoImpl;
import ua.nix.jdbc.entity.ModelGenerator;
import ua.nix.jdbc.entity.model.Path;
import ua.nix.jdbc.util.Constants;
import ua.nix.libs.routecost.RouteCost;
import ua.nix.libs.entity.Node;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Service {
    private final DaoImpl dao;

    public Service() {
        dao = new DaoImpl();
    }

    public void start(String input) {
        Properties properties = getPropertiesForPostgre();
        try (Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            connection.setAutoCommit(false);
            dao.insertDataIntoLocations(connection);
            dao.insertDataIntoRoutes(connection);
            dao.insertDataIntoProblems(connection, input);
            dao.insertDataIntoSolutions(connection);
            List<Path> paths = dao.getUnresolvedRoutes(connection);
            RouteCost routeCost = new RouteCost();
            for (Path path : paths) {
                List<Node> nodes = RouteCost.getInstance().generate(ModelGenerator.getInputData());
                int result = routeCost.result(nodes, path.getStringPath());
                dao.modifiedSolutions(connection, result, path);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private Properties getPropertiesForPostgre(){
        Properties properties = new Properties();
        try(InputStream inputStream = Service.class.getResourceAsStream(Constants.PROPERTY_FILE_NAME.getValue())) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.err.println("File not found. Please check the file path.");
        }
        return properties;
    }
}
