package ua.nix.finance.service;

import ua.nix.finance.persistence.entity.DischargeDto;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JDBCService {

    private static JDBCService instance;
    private Connection connection;

    private JDBCService(Connection connection) {
        this.connection = connection;
    }

    public List<DischargeDto> getDischarge(Long id, String dateFrom, String dateTo) {
        List<DischargeDto> resultList = new ArrayList<>();
        Timestamp timestampFrom = Timestamp.from(Instant.parse(dateFrom + "Z"));
        Timestamp timestampTo = Timestamp.from(Instant.parse(dateTo + "Z"));
        try (PreparedStatement dischargeTake = connection.prepareStatement(
                "select transactions.id, transactions.funds, transactions.date_time, categories.categorytype, categories.name " +
                        "from transactions " +
                        "join transactions_categories " +
                        "on transactions_categories.transaction_id = transactions.id " +
                        "join categories on " +
                        "transactions_categories.category_id = categories.id " +
                        "join accounts on " +
                        "accounts.id = ? " +
                        "and transactions.date_time between ? and ?")) {
            dischargeTake.setLong(1, id);
            dischargeTake.setTimestamp(2, timestampFrom);
            dischargeTake.setTimestamp(3, timestampTo);
            ResultSet resultSet = dischargeTake.executeQuery();
            while (resultSet.next()) {
                DischargeDto dischargeDto = new DischargeDto();
                dischargeDto.setId(resultSet.getString(1));
                dischargeDto.setFunds(resultSet.getString(2));
                dischargeDto.setDateTime(resultSet.getString(3));
                dischargeDto.setType(resultSet.getString(4));
                dischargeDto.setName(resultSet.getString(5));
                resultList.add(dischargeDto);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return resultList;
    }

    public Integer getDebet(Long id, String dateFrom, String dateTo) throws SQLException {
        Timestamp timestampFrom = Timestamp.from(Instant.parse(dateFrom + "Z"));
        Timestamp timestampTo = Timestamp.from(Instant.parse(dateTo + "Z"));
        try (PreparedStatement debetTakeStatement = connection.prepareStatement(
                "select sum(transactions.funds) " +
                        "from transactions " +
                        "where funds > 0 " +
                        "and account = ? " +
                        "and date_time between ? and ?")) {
            debetTakeStatement.setLong(1, id);
            debetTakeStatement.setTimestamp(2, timestampFrom);
            debetTakeStatement.setTimestamp(3, timestampTo);
            ResultSet resultSet = debetTakeStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage());
        }
    }

    public Integer getBalance(Long id, Integer debet, String dateFrom, String dateTo) throws SQLException {
        Timestamp timestampFrom = Timestamp.from(Instant.parse(dateFrom + "Z"));
        Timestamp timestampTo = Timestamp.from(Instant.parse(dateTo + "Z"));
        try (PreparedStatement balanceTakeStatement = connection.prepareStatement(
                "select sum(transactions.funds) " +
                        "from transactions " +
                        "where funds < 0 " +
                        "and account = ?" +
                        "and date_time between ? and ?")) {
            balanceTakeStatement.setLong(1, id);
            balanceTakeStatement.setTimestamp(2, timestampFrom);
            balanceTakeStatement.setTimestamp(3, timestampTo);
            ResultSet resultSet = balanceTakeStatement.executeQuery();
            resultSet.next();
            return debet - Math.abs(resultSet.getInt(1));
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage());
        }
    }

    public static JDBCService getInstance(Connection connection) {
        if (instance == null) {
            instance = new JDBCService(connection);
        }
        return instance;
    }
}
