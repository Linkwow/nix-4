package ua.nix.jdbc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ua.nix.jdbc.entity.ModelGenerator;
import ua.nix.jdbc.entity.model.*;

public class DaoImpl {
    private final ModelGenerator modelGenerator;

    public DaoImpl() {
        modelGenerator = new ModelGenerator();
    }

    public void insertDataIntoLocations(Connection connection) throws SQLException {
        List<Location> locations = modelGenerator.generateLocations();
        try (PreparedStatement insertIntoLocation = connection.prepareStatement("INSERT INTO locations (id, name) VALUES (?, ?) ON CONFLICT DO NOTHING")) {
            for (Location location : locations) {
                insertIntoLocation.setInt(1, location.getId());
                insertIntoLocation.setString(2, location.getName());
                insertIntoLocation.addBatch();
            }
            insertIntoLocation.executeBatch();
            connection.commit();
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
            throw new SQLException();
        }
    }

    public void insertDataIntoRoutes(Connection connection) throws SQLException {
        List<Route> routes = modelGenerator.generateRoutes();
        try (PreparedStatement insertIntoRoutes = connection.prepareStatement(
                "INSERT INTO routes (id, from_id, to_id, cost) VALUES (?, ?, ?, ?) ON CONFLICT DO NOTHING")) {
            for (Route route : routes) {
                insertIntoRoutes.setInt(1, route.getId());
                insertIntoRoutes.setInt(2, route.getFromId());
                insertIntoRoutes.setInt(3, route.getToId());
                insertIntoRoutes.setInt(4, route.getCost());
                insertIntoRoutes.addBatch();
            }
            insertIntoRoutes.executeBatch();
            connection.commit();
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
            throw new SQLException();
        }
    }

    public void insertDataIntoProblems(Connection connection, String input) throws SQLException {
        List<Problem> problems = createProblems(connection, input);
        try (PreparedStatement insertIntoProblems = connection.prepareStatement(
                "INSERT INTO Problems (id, from_id, to_id) VALUES (?, ?, ?) ON CONFLICT DO NOTHING")) {
            for (Problem problem : problems) {
                insertIntoProblems.setInt(1, problem.getId());
                insertIntoProblems.setInt(2, problem.getFromId());
                insertIntoProblems.setInt(3, problem.getToId());
                insertIntoProblems.addBatch();
            }
            insertIntoProblems.executeBatch();
            connection.commit();
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
            throw new SQLException();
        }
    }

    public void insertDataIntoSolutions(Connection connection) throws SQLException {
        try (PreparedStatement insertIntoSolutions = connection.prepareStatement(
                "INSERT INTO solutions (id, problem_id) VALUES (?, ?) ON CONFLICT DO NOTHING")) {
            List<Solution> solutions = createSolution(connection);
            for (Solution solution : solutions) {
                insertIntoSolutions.setInt(1, solution.getId());
                insertIntoSolutions.setInt(2, solution.getProblemId());
                insertIntoSolutions.addBatch();
            }
            insertIntoSolutions.executeBatch();
            connection.commit();
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
            throw new SQLException();
        }
    }

    public List<Integer> selectFromProblemsIdToSolutions(Connection connection) throws SQLException {
        List<Integer> idList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id from Problems ");
            while (resultSet.next()) {
                idList.add(resultSet.getInt(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new SQLException();
        }
        return idList;
    }

    public List<Path> getUnresolvedRoutes(Connection connection) throws SQLException {
        List<Path> paths = new ArrayList<>();
        int fromCity, toCity;
        try (Statement getFromProblems = connection.createStatement()) {
            ResultSet resultSetFromProblems = getFromProblems.executeQuery(
                    "SELECT solutions.id, from_id, to_id " +
                            "FROM problems " +
                            "LEFT JOIN solutions " +
                            "ON problems.id = solutions.id " +
                            "AND solutions.cost IS NULL");
            while (resultSetFromProblems.next()) {
                Path path = new Path();
                path.setSolutionId(resultSetFromProblems.getInt(1));
                fromCity = resultSetFromProblems.getInt(2);
                toCity = resultSetFromProblems.getInt(3);
                try (PreparedStatement getFromLocations = connection.prepareStatement("SELECT name FROM Locations WHERE id = ?")) {
                    getFromLocations.setInt(1,fromCity);
                    ResultSet resultSetFromLocations = getFromLocations.executeQuery();
                    while(resultSetFromLocations.next()) {
                        path.setFromCity(resultSetFromLocations.getString("name"));
                    }
                }
                try (PreparedStatement getToLocations = connection.prepareStatement("SELECT name FROM Locations WHERE id = ?")) {
                    getToLocations.setInt(1,toCity);
                    ResultSet resultSetFromLocations = getToLocations.executeQuery();
                    while(resultSetFromLocations.next()) {
                        path.setToCity(resultSetFromLocations.getString("name"));
                    }
                }
                paths.add(path);
            }
            return paths;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new SQLException();
        }
    }

    public void modifiedSolutions(Connection connection, int costs, Path path) throws SQLException {
        try (PreparedStatement setSolutions = connection.prepareStatement("UPDATE solutions SET cost = ? WHERE id = ?")) {
            setSolutions.setInt(1, costs);
            setSolutions.setInt(2, path.getSolutionId());
            setSolutions.addBatch();
            setSolutions.executeBatch();
            connection.commit();
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
            throw new SQLException(sqlException);
        }
    }

    private List<Problem> createProblems(Connection connection, String  inputData) throws SQLException {
        String[] allData = inputData.split("/");
        String[] names;
        List<Problem> problems = new ArrayList<>();
        for (int index = 0, id = 1; index < Integer.parseInt(allData[0]); index++, id++) {
            names = allData[id].split(" ");
            try {
                problems.add(modelGenerator.generateProblem(id, this.getIdFromLocation(connection, names[0]), this.getIdFromLocation(connection, names[1])));
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
                throw new SQLException();
            }
        }
        return problems;
    }

    private Integer getIdFromLocation(Connection connection, String name) throws SQLException {
        int id = 0;
        try (PreparedStatement getFromProblems = connection.prepareStatement("SELECT id FROM locations WHERE name = ?")) {
            getFromProblems.setString(1, name);
            ResultSet resultSet = getFromProblems.executeQuery();
           while (resultSet.next()) {
               id = resultSet.getInt(1);
           }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new SQLException(sqlException);
        }
        return id;
    }

    private List<Solution> createSolution(Connection connection) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        try {
            List<Integer> problemsId = selectFromProblemsIdToSolutions(connection);
            for (int solutionId = 1, problemId = 0; solutionId < problemsId.size() + 1; solutionId++, problemId++) {
                solutions.add(modelGenerator.generateSolution(solutionId, problemsId.get(problemId)));
            }
            return solutions;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new SQLException();
        }
    }
}
