package ua.nix.finance.service;

import ua.nix.finance.persistence.entity.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCService {

    private static JDBCService instance;
    private Connection connection;

    private JDBCService(Connection connection) {
        this.connection = connection;
    }

 /*   public List<Transaction> getDischarge(Long id, String dateFrom, String dateTo) {
        try (Statement dischargeTake = connection.createStatement()) {
            ResultSet resultSet


        } catch (SQLException sqlException) {

        }

    }*/

    public static JDBCService getInstance(Connection connection) {
        if (instance == null) {
            instance = new JDBCService(connection);
        }
        return instance;
    }
}
