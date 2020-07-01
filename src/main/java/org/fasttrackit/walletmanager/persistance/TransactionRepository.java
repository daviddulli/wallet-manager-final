package org.fasttrackit.walletmanager.persistance;

import org.fasttrackit.walletmanager.domain.Transaction;
import org.fasttrackit.walletmanager.transfer.CreateTransactionRequest;
import org.fasttrackit.walletmanager.transfer.UpdateTransactionRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    public void createTransaction(CreateTransactionRequest request) throws IOException, SQLException, ClassNotFoundException {
        // preventing SQL Injection by avoiding concatenation and using PreparedStatement
        String sql = "INSERT INTO transaction (description, value, date, type) VALUES (?,?,?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, request.getDescription());
            preparedStatement.setDouble(2, request.getValue());
            preparedStatement.setDate(3, request.getDate());
            preparedStatement.setString(4, request.getType());

            preparedStatement.executeUpdate();
        }
    }

    public void updateTransaction(long id, UpdateTransactionRequest request) throws IOException, SQLException, ClassNotFoundException {
        // preventing SQL Injection by avoiding concatenation and using PreparedStatement
        String sql = "UPDATE transaction SET value WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, request.getValue());
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        }
    }

    public void deleteTransaction(long id) throws IOException, SQLException, ClassNotFoundException {
        // preventing SQL Injection by avoiding concatenation and using PreparedStatement
        String sql = "DELETE FROM transaction WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public List<Transaction> getTransaction() throws IOException, SQLException, ClassNotFoundException {
        String sql = "SELECT id, description, value, date, type  FROM transaction ";

        try (Connection connection = DatabaseConfiguration.getConnection();
             // Statement should be used only for no parameter queries
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Transaction> transactions = new ArrayList<>();

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getLong("id"));
                transaction.setDescription(resultSet.getString("description"));
                transaction.setDate(resultSet.getDate("date"));
                transaction.setType(resultSet.getString("type"));
                transaction.setValue(resultSet.getDouble("value"));
                transactions.add(transaction);
            }

            return transactions;
        }

    }
}