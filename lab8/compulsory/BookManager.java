package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookManager {
    // Singleton instance of the Database
    private static final Database databaseManager = Database.getInstance();

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Getting a connection from the database manager
            connection = databaseManager.getConnection();
            connection.setAutoCommit(false); // Start a transaction

            // Creating authors
            var authors = new AuthorDAO();
            authors.create("William Shakespeare");
            authors.create("Cassandra Clare");
            connection.commit();

            // Example SQL query
            String query = "SELECT * FROM authors";

            // Creating a prepared statement
            preparedStatement = connection.prepareStatement(query);

            // Executing the query
            resultSet = preparedStatement.executeQuery();

            // Processing the result set
            while (resultSet.next()) {
                // Process the result set here
                System.out.println(resultSet.getString("author_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Closing the connection
        databaseManager.closeConnection();
    }
}
